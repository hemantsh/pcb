package util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.FileStorageProperties;

import exception.FileStorageException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

@Component
public class FileStoreUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileStoreUtil.class);
	
	private  FileStorageProperties fileStorageProperties;
	
	
	@Autowired
	public FileStoreUtil(FileStorageProperties fileStorageProperties) {
		super();
		this.fileStorageProperties = fileStorageProperties;
	}

	//Create folder in the root of upload dir (fileStorageProperties.uploadDir- S3 or file system)
//	public Path createFolder(String name) {
//		Path folder = Paths.get(fileStorageProperties.getUploadDir() + "/" + name)
//              .toAbsolutePath().normalize();
//
//	    try {
//	        Files.createDirectories(folder);
//	    } catch (Exception ex) {
//	    	folder = null;
//	        throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//	    }
//	    return folder;
//	}
	
	public String storeFile(String projectId, MultipartFile file) throws IOException {
        
        Path folder = Paths.get(fileStorageProperties.getUploadDir() +"/" + projectId ).toAbsolutePath().normalize();
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
		
		return StringUtils.cleanPath(file.getOriginalFilename());
    
    }
	
//	private String storeFile(InputStream inpStream, Path folder, String fileName) {
//        
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = folder.resolve(fileName);
//            Files.copy(inpStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            return fileName;
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
	
	//Extrat files from ZIP (fileName) into given folder
	public void extractFiles(String projectId, String fileName) {
		
		Path folder = Paths.get(fileStorageProperties.getUploadDir() +"/" + projectId + "/").toAbsolutePath().normalize();
        
		try {
			
			ZipFile zipFile = new ZipFile(folder.resolve(fileName).normalize().toFile());
			zipFile.extractAll(folder.toString());
			
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public List<String> listFiles(String projectId) {
		
		Path folder = Paths.get(fileStorageProperties.getUploadDir() +"/" + projectId + "/").toAbsolutePath().normalize();
        
		List<String> extractedFiles = new ArrayList<String>();
		
		try (Stream<Path> paths = Files.walk(Paths.get(folder.toString()))) {
    	    paths
    	        .filter(Files::isRegularFile)
    	        .forEach( file -> {
    	        	
    	        	extractedFiles.add( file.getFileName().toString());
    	        });
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		
		return extractedFiles;
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
	
}
