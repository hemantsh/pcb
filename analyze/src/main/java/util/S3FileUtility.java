package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


@Component
public class S3FileUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(S3FileUtility.class);
	private static AmazonS3 s3client;
	
	private static FileStorageProperties fileStorageProperties;
	private static S3FileUtility instance;
	
	public static S3FileUtility getInstance( FileStorageProperties fileStorageProperties) {
		if(instance == null ) {
			instance = new S3FileUtility(fileStorageProperties);
		}
		return instance;
	}
	
	@Autowired
	private S3FileUtility(FileStorageProperties fileStorageProperties) {
		super();
		
		S3FileUtility.fileStorageProperties = fileStorageProperties;
		//S3 credentials
		AWSCredentials credentials = new BasicAWSCredentials(
				fileStorageProperties.getAccessKey(), //Key is defined in ENV variable
				fileStorageProperties.getSecretKey()  //Key is defined in ENV variable
		);
		//Create client
		S3FileUtility.s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.US_WEST_1)
				  .build();
	}
	
	
	/**
	 * Store the file in S3 location
	 * @param projectId
	 * @param file
	 * @throws IOException
	 */
	public void storeFile(String projectId, MultipartFile file) throws IOException {
		
		//Save extract files locally first
		FileUtil.saveAndExtractZip(projectId, file, fileStorageProperties);
		
		Path folder = Paths.get(fileStorageProperties.getUploadDir() + "/" + projectId ).toAbsolutePath().normalize();
		Path tempFile = Paths.get(folder + "/" + file.getOriginalFilename()).toAbsolutePath().normalize();
		
		//Save zip file on S3
		saveFile(projectId, file, tempFile);
		
		//For each extracted files on local, transfer to same structure on S3
		try (Stream<Path> paths = Files.walk(Paths.get(folder.toString()))) {
    	    paths.filter(Files::isRegularFile)
    	        .forEach( pathFile -> {
    	        	
    	        	try {
						saveFile( projectId, pathFile.toFile() );
					} catch (IOException e) {
						logger.error("Error storing file in S3", e);
						//e.printStackTrace();
					}

    	        });
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		//Delete the local folder as we moved all files to S3
		FileUtil.deleteFolder(folder.toFile());
		
	}

	/**
	 * Save file to S3
	 * @param projectId
	 * @param file
	 * @param tempFile
	 */
	private  void saveFile(String projectId, MultipartFile file, Path tempFile)  {
		s3client.putObject(
			fileStorageProperties.getBucket(), 
			projectId +"/"+ file.getOriginalFilename(), 
			tempFile.toFile()
		);
		
	}
	
	/**
	 * Save extracted file to S3. 
	 * Only take relative path of file starting from projectId
	 * @param projectId
	 * @param file
	 * @throws IOException
	 */
	private void saveFile(String projectId, File file) throws IOException {
		
		Path folder = Paths.get(fileStorageProperties.getUploadDir() +"/" + projectId ).toAbsolutePath().normalize();
		
		s3client.putObject(
			fileStorageProperties.getBucket(), 
			projectId + "/"+ file.toString().replace(folder.toString()+"/", ""), 
			file
		);
	}
	
	/**
	 * Listing of project files in S3 bucket
	 * @param projectId
	 * @return
	 */
	public List<String> listObjects(String projectId) {
		
		List<String> names = new ArrayList<String>();
		
		ObjectListing objectListing = s3client.listObjects(fileStorageProperties.getBucket(), projectId);
		for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
			names.add(os.getKey());
		}
		
		return names;
	}

}
