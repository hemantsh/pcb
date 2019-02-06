package com.sc.fe.analyze.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.datastax.driver.core.utils.UUIDs;
import com.sc.fe.analyze.FileStorageProperties;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.PCBInformation;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.util.ErrorCodeMap;
import com.sc.fe.analyze.util.FileStoreUtil;
import com.sc.fe.analyze.util.FileUtil;
import com.sc.fe.analyze.util.GerberFileProcessingUtil;
import com.sc.fe.analyze.util.MappingUtil;
import com.sc.fe.analyze.util.ODBProcessing;
import com.sc.fe.analyze.util.ReportUtility;
import java.util.NoSuchElementException;

/**
 *
 * @author Hemant
 */
@Service
public class FileExtractUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileExtractUploadService.class);
    private FileStoreUtil util;

    //private S3FileUtility util;
    @Autowired
    BaseService baseService;   
    @Autowired
    ProjectFilesService projectFilesService;
    @Autowired
    ProjectService projectService;

    /**
     *
     * @param fileStorageProperties - property file containing file upload
     * options
     */
    @Autowired
    public FileExtractUploadService(FileStorageProperties fileStorageProperties) {
        this.util = FileStoreUtil.getInstance(fileStorageProperties); //For local file
        //this.util = S3FileUtility.getInstance(fileStorageProperties); 	
    }

    /**
     * Upload, extract and validates files
     *
     * @param file - the file to be uploaded
     * @param inputs - the inputs of CustomerInputs
     * @return Report - report with validation status
     */
    public Report uploadAndExtractFile(MultipartFile file,
            CustomerInformation inputs,
            PCBInformation boardInfo) throws Exception {

        ProjectDetails projectDetails = new ProjectDetails();
        projectDetails.setProjectId(inputs.getProjectId());

        Report report = validateFiles(projectDetails);       

        logger.debug("****** Done generating report *******");

        //To delete the folder 
        Path folder = Paths.get(util.getUploadDir() + File.separator + projectDetails.getProjectId()).toAbsolutePath().normalize();
        FileUtil.deleteFolder(folder.toFile());
        return report;
    }

    /**
     * Validates the project files and send a report.
     *
     * @param projectDetails
     * @return
     */
    public Report validateFiles(ProjectDetails projectDetails) {

        List<FileDetails> fileDetails = projectDetails.getFileDetails();
        //If no file details,  try to create it
        if (fileDetails == null || fileDetails.size() <= 0) {
            Set<String> allFiles = util.listFiles(projectDetails.getProjectId());
            allFiles.stream().forEach(name -> {
                FileDetails fd = new FileDetails();
                fd.setName(name);
                fileDetails.add(fd);
            });
        }
        /// REPORT
        Report report = new Report();
        report.setProjectDetail(projectDetails);
        report.setSummary("****** File upload and basic validation by name and extension. *******");

        List<String> requiredFilesTypes = baseService.getServiceFiles(
                MappingUtil.getServiceId(
                        projectDetails.getServiceType()
                )
        );

        List<String> availFileTypes = projectDetails.getFileDetails().stream()
                .filter(fd -> fd.getType() != null)
                .map(FileDetails::getType)
                .collect(Collectors.toList());

        List<String> missingTypes = new ArrayList<String>();

        if (availFileTypes != null) {

            if (!availFileTypes.containsAll(requiredFilesTypes)) {
                missingTypes.addAll(requiredFilesTypes);
                missingTypes.removeAll(availFileTypes);
            }
        }

        if (missingTypes.size() > 0) {
            report.setValidationStatus("We found some missing information. ");
            missingTypes.stream().forEach(type -> {
                report.addError(type);
                report.addErrorCode(ErrorCodeMap.getCodeForFileType(type));
            });
        } else {
            report.setValidationStatus("Matched with all required file types. All information collected.");
        }
        return report;
    }

    public void save(ProjectDetails projectDetails) {
        // TODO Auto-generated method stub
        //If projectID/R# is not there, get it from FEMS API call. Stub the call for now
        //Check if new version is required or its an add/replace for existing version.

        String version = getVersion(projectDetails);
        String projectId = getProjectId(projectDetails);

        projectDetails.setProjectId(projectId);
        projectDetails.setVersion(version);

        projectDetails.getFileDetails().stream().forEach(fd -> {
            ProjectFiles pFiles = ReportUtility.convertToDBObject(fd);
            pFiles.setVersion(UUID.fromString(version));
            pFiles.setProjectId(projectId);
            projectFilesService.save(pFiles);
        });

        //TODO: Save into project and project_file table               
        projectService.save(ReportUtility.convertToDBObject(projectDetails));
    }

    private String getProjectId(ProjectDetails projectDetails) {
        String projectId = null;

        if (!StringUtils.isEmpty(projectDetails.getProjectId())) {
            return projectDetails.getProjectId();
        }

        if (!projectDetails.isNewProject()) {
            projectId = getProjectIdByCustomerId(projectDetails.getCustomerId());            
            if (StringUtils.isEmpty(projectId)) {
                projectId = getProjectIdByCustomerEmail(projectDetails.getEmailAddress());
            }
            if (StringUtils.isEmpty(projectId)) {
                projectId = getProjectIdByZipName(projectDetails.getZipFileName());
            }
        }
        if (StringUtils.isEmpty(projectId)) {            
            projectId = Long.toHexString(Double.doubleToLongBits(Math.random()));
        }

        return projectId;
    }

    private String getProjectIdByCustomerId(String customerId) {
        String projectId = null;
        if (StringUtils.isEmpty(customerId)) {
            return projectId;
        } else {
            List<ProjectDetails> projDtl = projectService.findByCustomerId(customerId);            
            if (projDtl != null && projDtl.size() > 0) {                                    
                System.out.println("Size is---"+projDtl.size());                
                ProjectDetails latestRecord = projDtl.stream()
                    .max((a1,a2)->a1.getCreateDate().compareTo(a2.getCreateDate())).orElseThrow(NoSuchElementException::new);             
                projectId=latestRecord.getProjectId();
                //To check the latest Record
//                System.out.println("Date is--"+latestRecord.getCreateDate());
//                System.out.println("Version is--"+latestRecord.getVersion());               
               
            }
        }       
        return projectId;
    }

    private String getProjectIdByCustomerEmail(String emailId) {
        String projectId = null;
        if (StringUtils.isEmpty(emailId)) {
            return projectId;
        }
           
        List<ProjectDetails> projDtl = projectService.findByCustomerEmail(emailId);        
        if (projDtl != null && projDtl.size() > 0) {
             ProjectDetails latestRecord = projDtl.stream()
                    .max((a1,a2)->a1.getCreateDate().compareTo(a2.getCreateDate())).orElseThrow(NoSuchElementException::new);             
            projectId=latestRecord.getProjectId();
            
        }
        return projectId;
    }

    private String getProjectIdByZipName(String zipFileName) {
        String projectId = null;
        if (StringUtils.isEmpty(zipFileName)) {
            return projectId;
        }       
        List<ProjectDetails> projDtl = projectService.findByZipFileName(zipFileName);
        if (projDtl != null && projDtl.size() > 0) {
            ProjectDetails latestRecord = projDtl.stream()
                    .max((a1,a2)->a1.getCreateDate().compareTo(a2.getCreateDate())).orElseThrow(NoSuchElementException::new);             
            projectId=latestRecord.getProjectId();            
        }
        return projectId;
    }

    private String getVersion(ProjectDetails projectDetails) {

        String version = null;

        if (projectDetails.isAttachReplace()) {
            return projectDetails.getVersion();
        } else {
            version = UUIDs.timeBased().toString();
        }
        return version;
    }

    public void validateFiles(String projectId) {
        //TODO: implememt
        //Get the projectDetails by projectId
        //call validateFiles( ProjectDetails projectDetails ) to get results
    }

    /**
     * Extract and save the zip file. No validations.
     *
     * @param file
     * @param inputs
     * @return
     * @throws IOException
     */
    public Set<String> extractAndSaveFiles(MultipartFile file, CustomerInformation inputs) throws IOException {
        // Local file based
        String fileName = util.storeFile(inputs.getProjectId(), file);
        util.extractFiles(inputs.getProjectId(), fileName);
        //Path folder = Paths.get(util.getUploadDir() + File.separator + inputs.getProjectId() + File.separator).toAbsolutePath().normalize();
        // END local	

        //S3 Based
        //util.storeFile(inputs.getProjectId(), file);
        //report.setExctractedFileNames( util.listObjects(inputs.getProjectId()) );
        // end S3
        return util.listFiles(inputs.getProjectId());
    }

    /**
     * Performs all possible Gerber file processing.
     *
     * @param fileDetails - These given file details will be updated if we find
     * more details during processing
     */
    private void processGerber(List<FileDetails> fileDetails) {

        GerberFileProcessingUtil.processFilesByExtension(fileDetails, baseService.getExtensionToFileMapping());

        //For each file that is gerber format
        fileDetails.stream()
                //.filter( fd -> "gerber".equalsIgnoreCase( fd.getFormat()) ) //TODO: add later
                .forEach(fileDtl -> {
                    //Apply rules by name pattern
                    GerberFileProcessingUtil.parseFileName(fileDtl);
                });
    }

    /**
     * ODB processing. Mainly parse matrix file to get fileDetils
     *
     * @param folder
     * @param projectId
     * @return
     */
    private List<FileDetails> processODB(Path folder, String projectId) {
        //To check that whether file type is ODB or not.
        File[] listOfFiles = folder.toFile().listFiles();
        List<FileDetails> fdList = new ArrayList<FileDetails>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isDirectory()) {
                if (listOfFiles[i].getName().toLowerCase().equals("odb")) {
                    Path checkODBFolder = Paths.get(util.getUploadDir() + File.separator + projectId + File.separator + listOfFiles[i].getName() + File.separator + "matrix" + File.separator + "matrix").toAbsolutePath().normalize();
                    if (checkODBFolder.toFile().exists()) {
                        fdList = ODBProcessing.processODB(checkODBFolder);
                        //Print Result 
                        //fdList.stream().forEach(fd->
                        //System.out.println(fd.getName()));
                    }
                }
            }
        }
        return fdList;
    }

}
