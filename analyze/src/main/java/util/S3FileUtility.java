package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.sc.fe.analyze.FileStorageProperties;

import exception.FileStorageException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;


@Component
public class S3FileUtility {
	
	private AmazonS3 s3client;
	private FileStorageProperties fileStorageProperties;
	
	@Autowired
	public S3FileUtility(FileStorageProperties fileStorageProperties) {
		super();
		
		this.fileStorageProperties = fileStorageProperties;
		AWSCredentials credentials = new BasicAWSCredentials(
				fileStorageProperties.getAccessKey(), 
				fileStorageProperties.getSecretKey()
		);
		
		this.s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.US_WEST_1)
				  .build();
	}
	
	public AmazonS3 getS3Client() {
		return s3client;
	}
	
	public void storeFile(String projectId, MultipartFile file) throws IOException {
		
		Path folder = Paths.get(fileStorageProperties.getUploadDir() +"/" + projectId );
		if( ! Files.exists(folder)) {
        	try {
    	        Files.createDirectories(folder);
    	    } catch (Exception ex) {
    	    	folder = null;
    	        throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
    	    }
        }
		Path tempFile = Paths.get(folder + "/" + file.getOriginalFilename()).toAbsolutePath().normalize();
		Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
		
		saveFile(projectId, file, tempFile);
		
		try {
			
			ZipFile zipFile = new ZipFile(folder.resolve(tempFile).normalize().toFile());
			zipFile.extractAll(folder.toString());
			
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (Stream<Path> paths = Files.walk(Paths.get(folder.toString()))) {
    	    paths.filter(Files::isRegularFile)
    	        .forEach( pathFile -> {
    	        	
    	        	try {
						saveFile( projectId, pathFile.toFile() );
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

    	        });
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		
		deleteFolder(folder.toFile());
		
	}

	private void saveFile(String projectId, MultipartFile file, Path tempFile)  {
		s3client.putObject(
			fileStorageProperties.getBucket(), 
			projectId +"/"+ file.getOriginalFilename(), 
			tempFile.toFile()
		);
		
	}
	
	private void saveFile(String projectId, File file) throws IOException {
		Path folder = Paths.get(fileStorageProperties.getUploadDir() +"/" + projectId );
		
		s3client.putObject(
			fileStorageProperties.getBucket(), 
			projectId + "/"+ file.toString().replace(folder.toString()+"/", ""), 
			file
		);
	}
	
	public List<String> listObjects(String projectId) {
		
		List<String> names = new ArrayList<String>();
		
		ObjectListing objectListing = s3client.listObjects(fileStorageProperties.getBucket(), projectId);
		for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
			names.add(os.getKey());
		}
		
		return names;
	}

	/**
	 * @return the fileStorageProperties
	 */
	public FileStorageProperties getFileStorageProperties() {
		return fileStorageProperties;
	}

	/**
	 * @param fileStorageProperties the fileStorageProperties to set
	 */
	public void setFileStorageProperties(FileStorageProperties fileStorageProperties) {
		this.fileStorageProperties = fileStorageProperties;
	}

	private void deleteFolder(File file) {
		if(file.isDirectory()) {
			if( file.list().length == 0 ) {
				file.delete();
			}else {
				for (String temp : file.list()) {
					deleteFolder( new File( file, temp) );
				}
				if(file.list().length==0){
					file.delete();
				}
			}
		}else {
			file.delete();
		}
	}
}
