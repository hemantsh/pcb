package com.sc.fe.analyze.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.FileStorageProperties;

import exception.FileStorageException;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 *
 * @author Hemant
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * Save the file based on projectID
     *
     * @param projectId Normally each project create a sub folder in upload
     * directory
     * @param file the file to be uploaded
     * @param fileStorageProperties property file containing file upload options
     * @return location of the file
     */
    public static String saveUploadedZipFile(String projectId, MultipartFile file, FileStorageProperties fileStorageProperties) throws IOException {

        //New folder for each project
        Path folder = Paths.get(fileStorageProperties.getUploadDir() + File.separator + projectId).toAbsolutePath().normalize();
        //If folder does not exist, create it
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (Exception ex) {
                folder = null;
                logger.error("Could not create the directory where the uploaded files will be stored.", ex);
                throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
            }
        }
        //Copy file as same name in the folder
        Path tempFile = Paths.get(folder + File.separator + file.getOriginalFilename()).toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        return StringUtils.cleanPath(file.getOriginalFilename());
    }

    /**
     * Save and extract the zip file locally, under the project sub-folder
     *
     * @param projectId Normally each project create a sub folder in upload
     * directory
     * @param file the file to be uploaded
     * @param fileStorageProperties property file containing file upload options
     */
    public static void saveAndExtractZip(String projectId, MultipartFile file, FileStorageProperties fileStorageProperties) throws IOException {
        Path folder = Paths.get(fileStorageProperties.getUploadDir() + File.separator + projectId + File.separator).toAbsolutePath().normalize();
        //If folder does not exist, create it
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
            } catch (Exception ex) {
                folder = null;
                logger.error("Could not create the directory where the uploaded files will be stored.", ex);
                throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
            }
        }
        //Copy file as same name in the folder
        Path tempFile = Paths.get(folder + File.separator + file.getOriginalFilename()).toAbsolutePath().normalize();
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            ZipFile zipFile = new ZipFile(folder.resolve(fileName).normalize().toFile());
            zipFile.extractAll(folder.toString());

        } catch (ZipException e) {
            logger.error("Failed to unzip the file. " + e.getMessage(), e);
            //e.printStackTrace();
        }
    }

    /**
     * Delete the folder recursively. If files are there they will be deleted
     *
     * @param file the name of the folder which will be deleted
     */
    public static void deleteFolder(File file) {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                for (String temp : file.list()) {
                    deleteFolder(new File(file, temp));
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            file.delete();
        }
    }
}
