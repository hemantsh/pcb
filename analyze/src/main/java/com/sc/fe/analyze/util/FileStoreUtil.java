package com.sc.fe.analyze.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.FileStorageProperties;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author Hemant
 */
@Component
public class FileStoreUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileStoreUtil.class);
    private static FileStoreUtil instance;

    private FileStorageProperties fileStorageProperties;

    /**
     *
     * @param fileStorageProperties from fileStorageProperties to set instance
     * @return the FileStroeUtil
     */
    public static FileStoreUtil getInstance(FileStorageProperties fileStorageProperties) {
        if (instance == null) {
            instance = new FileStoreUtil(fileStorageProperties);
        }
        return instance;
    }

    /**
     *
     * @return the upload Directory name
     */
    public String getUploadDir() {
        return fileStorageProperties.getUploadDir();
    }

    @Autowired
    private FileStoreUtil(FileStorageProperties fileStorageProperties) {
        super();
        this.fileStorageProperties = fileStorageProperties;
    }

    /**
     * Store the uploaded file for given project. Overwrite existing file
     *
     * @param projectId store the projectId in the database
     * @param file store the filename in the database
     * @return in the String format
     */
    public String storeFile(String projectId, MultipartFile file) throws IOException {
        return FileUtil.saveUploadedZipFile(projectId, file, fileStorageProperties);
    }

    /**
     * Extract files from ZIP (fileName) into given folder
     *
     * @param projectId the project id
     * @param fileName the path of zip file under the project
     */
    public void extractFiles(String projectId, String fileName) {

        Path folder = Paths.get(fileStorageProperties.getUploadDir() + File.separator + projectId + File.separator).toAbsolutePath().normalize();
        try {

            ZipFile zipFile = new ZipFile(folder.resolve(fileName).normalize().toFile());
            zipFile.extractAll(folder.toString());

        } catch (ZipException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * List all files in the project folder
     *
     * @param projectId the project id
     * @return the all fileNames (extract from ZIP file)
     */
    public Set<String> listFiles(String projectId) {

        Path folder = Paths.get(fileStorageProperties.getUploadDir() + File.separator + projectId + File.separator).toAbsolutePath().normalize();

        Set<String> extractedFiles = new HashSet<String>();

        try (Stream<Path> paths = Files.walk(Paths.get(folder.toString()))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(file -> {

                        extractedFiles.add(file.getFileName().toString());

                    });
        } catch (IOException e) {
            logger.error("Error listing files in folder", e);
            e.printStackTrace();
        }

        return extractedFiles;

    }

    /**
     *
     * @return the fileStroageProperties
     */
    public FileStorageProperties getFileStorageProperties() {
        return fileStorageProperties;
    }

    /**
     *
     * @param fileStorageProperties - the fileStorageProperties to set
     */
    public void setFileStorageProperties(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }
}
