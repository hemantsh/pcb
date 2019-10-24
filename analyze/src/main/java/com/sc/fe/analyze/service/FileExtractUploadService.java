package com.sc.fe.analyze.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.datastax.driver.core.utils.UUIDs;
import com.google.gson.Gson;
import com.sc.fe.analyze.FileStorageProperties;
import com.sc.fe.analyze.data.entity.DifferenceReportJson;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.to.AttachementDetails;
import com.sc.fe.analyze.to.FileChange;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.util.AttachementProcessingUtil;
import com.sc.fe.analyze.util.CompareUtility;
import com.sc.fe.analyze.util.ErrorCodeMap;
import com.sc.fe.analyze.util.ErrorCodes;
import com.sc.fe.analyze.util.FileStoreUtil;
import com.sc.fe.analyze.util.GerberFileProcessingUtil;
import com.sc.fe.analyze.util.ReportUtility;

/**
 *
 * @author Hemant
 */
@Service
public class FileExtractUploadService extends BaseService {
    
    private static final Logger logger = LoggerFactory.getLogger(FileExtractUploadService.class);
    
    @Autowired
    private ProjectFilesService projectFilesService;
    @Autowired
    private ProjectService projectService;
    
    private FileStoreUtil fileUtil;

    /**
     *
     * @param fileStorageProperties property file containing file upload options
     */
    @Autowired
    public FileExtractUploadService(FileStorageProperties fileStorageProperties) {
        this.fileUtil = FileStoreUtil.getInstance(fileStorageProperties); //For local file
        //this.util = S3FileUtility.getInstance(fileStorageProperties); 	
    }

    public AttachementDetails saveAndProcessAttachement( MultipartFile zipFile, AttachementDetails attDetail ) throws Exception {
    	
    	int rand_int1 = Math.abs(ThreadLocalRandom.current().nextInt());
    	
    	String rNumber = String.valueOf(rand_int1);
    	fileUtil.storeFile(rNumber, zipFile);
    	fileUtil.extractFiles(rNumber, zipFile.getOriginalFilename());
    	
    	Path folder = Paths.get(fileUtil.getUploadDir() + File.separator + rNumber + File.separator).toAbsolutePath().normalize();
    	
    	return AttachementProcessingUtil.getAttachementDetails(folder, zipFile.getOriginalFilename() , extensionToFileMap() );
    }
    
    
    
    /**
     * Validates the project files and send a report.
     *
     * @param projectDetails Details of the project
     * @return the report
     */
    public Report validateFiles(ProjectDetails projectDetails) {
        // REPORT
        Report report = new Report();
        report.setProjectDetail(projectDetails);
        report.setSummary("****** File upload and basic validation by name and extension. *******");

//        //Check that if attachReplace=true in the JSON request but projectId is null then it give error
//        if (projectDetails.isAttachReplace() && (projectDetails.getProjectId() == null)) {
//            projectDetails.getErrors().put("V0000", "No data found for the rNumber. Submit again with newProject= true");
//            return report;
//        }
        //Check that user give correct Service Type or not
        //if (!projectDetails.isAttachReplace()) {
        //TODO: For new project do we need to check all the other project attributes ?
        //like - turnTimeQuantity,PCBClass,layers, etc.
        //Check that rNumber is given in the JSON request or not.
        if (StringUtils.isEmpty(projectDetails.getrNumber())) {
            projectDetails.getErrors().put("V0000", "rNumber is required");
            return report;
        }
        if (!isServiceTypeValid(projectDetails)) {
            return report;
        }
        //}

        //Check that user gives correct newProject and attachReplace values or not
        if (projectDetails.isNewProject() && projectDetails.isAttachReplace()) {
            projectDetails.getErrors().put("V0016", "Invalid Value of newProject and AttachReplace(Both values cannot be true).");
            return report;
        }
        //Check if project already exist for same zip file
        if( projectDetails.isNewProject() ) {
    		List<ProjectDetails> existingProj = projectService.findByZipFileName(projectDetails.getZipFileName());
    		if( existingProj != null && existingProj.size() > 0) {
    			//projectDetails.getErrors().put("V0021",ErrorCodes.V0021.getErrorMessage());
    			report.addErrorCode(ErrorCodes.V0021);
    		}
    	}
        

        //GoldenCheck
        List<String> missingTypes = validateGoldenCheckRules(projectDetails);
        List<ErrorCodes> missingTypeErrorCodes = nonSelectedFilesErorCodes(projectDetails);

        //Set Errors for those files which are missing
        if (missingTypes != null) {
            if (missingTypes.size() > 0) {
                report.setValidationStatus("We found some missing information. ");
                missingTypes.stream().forEach(type -> {
                    if (!StringUtils.isEmpty(type)) {
                        report.addError(type);
                        report.addErrorCode(ErrorCodeMap.getCodeForFileType(type.trim()));
                    }
                });
            } else {
                report.setValidationStatus("Matched with all required file types. All information collected.");
            }
        }
        Map<String, String> errMap = new HashMap<String, String>();
        
        if (report != null && report.getErrorCodes() != null) {
            report.getErrorCodes().stream().forEach(errCode -> {
                if (ErrorCodes.V0000 != errCode) {
                    errMap.put(errCode.toString(), errCode.getErrorMessage());
                    if (missingTypeErrorCodes.contains(errCode)) {
                        errMap.put(errCode.toString(), errCode.getErrorMessage() + " - [File Unselected]");
                    }
                }
            });
        }

        //Set errors for missing files or for not selected files
        projectDetails.setErrors(errMap);

        //Save  
        if (!StringUtils.isEmpty(projectDetails.getProjectId()) && !StringUtils.isEmpty(projectDetails.getSetId())) {
            projectService.save(ReportUtility.convertToDBObject(projectDetails));
        }
        
        return report;
    }
    
    public void compareProject(ProjectDetails projectDetails) {
        //compare the last ProjectDetails
        Map<String, String> compareMap = compareWithLastProjectData(projectDetails);
        String prevProjVersion = compareMap.get("version");
        String prevProjId = projectDetails.getProjectId();
        compareMap.remove("version");

        //save the compare results in another table
        //Only store comparison of latest set. table key = ProjectId. 
        //Also need to store the value of last version which was compared as non key column
        //Errors will be formated text. Add these to report.error field
        //projectDetails.setDifferences(CompareUtility.formatedError(compareMap));
        List<FileChange> fileChanges = CompareUtility.createFileChangeList(compareMap);
        projectDetails.setFileChanges(fileChanges);
        if( fileChanges != null && !fileChanges.isEmpty()) {
	        DifferenceReportJson diffReport = new DifferenceReportJson();
	        Gson gson = new Gson();
	        diffReport.setProjectId(prevProjId);
	        diffReport.setVersion(UUID.fromString(prevProjVersion));
	        diffReport.setDifferences(gson.toJson(fileChanges));
	        projectService.save(diffReport);
        }

    }

    /**
     * This method compares the projectDetails object from the last
     * projectDetails object from the database
     *
     * @param projectDetails Details of the project
     * @return the differences after comparing the latest project record from
     * the last project record
     */
    private Map<String, String> compareWithLastProjectData(ProjectDetails projectDetails) {
        
        Map<String, String> retErrors = new HashMap<String, String>();

        //Retrieve latest Record of similar project Id
        ProjectDetails prevprojDtl = getPreviousRecord(projectDetails);
        if (prevprojDtl != null) {
            //Retrieve attribute of ProjectDetails and FileDetails object of latest Record(from the database) and current Record.
            prevprojDtl = projectService.getProject(prevprojDtl.getProjectId(), prevprojDtl.getVersion());
            retErrors.put("version", prevprojDtl.getVersion());
            
            if (projectDetails.isAttachReplace()) {
                List<FileDetails> shortList = prevprojDtl.getFileDetails().stream().filter(fd -> projectDetails.getAllSelectedFileNames().contains(fd.getName())).collect(Collectors.toList());
                prevprojDtl.setFileDetails(shortList);
                retErrors.putAll(CompareUtility.compareFileDetails(projectDetails, prevprojDtl));
                return retErrors;
            }
            retErrors.putAll(CompareUtility.fullCompare(projectDetails, prevprojDtl));
        }
        return retErrors;
        
    }

    /**
     * @param projectDetails Details of the project
     * @return the list of required File types
     */
    private List<String> validateGoldenCheckRules(ProjectDetails projectDetails) {
        List<String> requiredFilesTypes = new ArrayList<>();
        int fabTurnTimeQtyFlag = 0, assTurnTimeQtyFlag = 0;
        String[] splitService = projectDetails.getServiceType().split(",");
        for (int i = 0; i < splitService.length; i++) {
            splitService[i] = splitService[i].toLowerCase().trim();

            //Check that serviceType is Assembly or not
            if (splitService[i].equals("assembly")) {
                assTurnTimeQtyFlag = 1;
                if ((projectDetails.getAssemblyTurnTimeQuantity() == null) || projectDetails.getAssemblyTurnTimeQuantity().isEmpty()) {
                    projectDetails.getErrors().put("V0018", "Assembly TurnTime Quantity missing");
                }
            }//Check that serviceType is Fabrication or not
            if (splitService[i].equals("fabrication")) {
                fabTurnTimeQtyFlag = 1;
                if ((projectDetails.getFabricationTurnTimeQuantity() == null) || projectDetails.getFabricationTurnTimeQuantity().isEmpty()) {
                    projectDetails.getErrors().put("V0019", "Fabrication TurnTime Quantity missing");
                }
            }
            
            int servId = getServiceId(splitService[i]);
            //Required files as per business rules        
            if (servId > 0) {
                requiredFilesTypes.addAll(getServiceFiles(servId));
            }
        }
        //Set the null values of Assembly and Fabrication turnTime Quantity if it is empty
        if (assTurnTimeQtyFlag == 0) {
            projectDetails.setAssemblyTurnTimeQuantity(null);
        }
        if (fabTurnTimeQtyFlag == 0) {
            projectDetails.setFabricationTurnTimeQuantity(null);
        }

        //Types provided by customer (Only selected ones)
        List<String> availFileTypes = getAvailableFileTypes(projectDetails.getFileDetails(), true);

        //Formats provided by customer(Only selected ones)
        Set<String> availFormats = projectDetails.getFileDetails().stream()
                .filter(fd -> fd.getFormat() != null)
                .filter(fd -> fd.isSelected())
                .map(FileDetails::getFormat)
                .collect(Collectors.toSet());
        
        availFileTypes.addAll(availFormats);

        //Find missing files types
        List<String> missing = CompareUtility.findMissingItems(requiredFilesTypes, availFileTypes);
        return missing;
    }
    
    private List<String> getAvailableFileTypes(List<FileDetails> fileDetails, boolean filterSelected) {
        //Types provided by customer (Only selected ones)
        List<String> availFileTypes = fileDetails.stream()
                .filter(fd -> fd.getType() != null)
                .filter(fd -> fd.isSelected() == filterSelected)
                .map(FileDetails::getType)
                .collect(Collectors.toList());
        
        List<String> upd_availFileTypes = new ArrayList<String>();
        //fileType can have multi values sep by , Here we make each of then separate.
        availFileTypes.stream().forEach(type -> {
            if (type.contains(",")) {
                String[] parts = type.split(",");
                upd_availFileTypes.addAll(Arrays.asList(parts));
            } else {
                upd_availFileTypes.add(type);
            }
        });
        availFileTypes.clear();
        availFileTypes.addAll(upd_availFileTypes);
        return availFileTypes;
    }

    //This function is used to retrieve those files which are not selected by user
    public List<ErrorCodes> nonSelectedFilesErorCodes(ProjectDetails projectDetails) {
        //Types provided by customer
        List<ErrorCodes> errCodes = new ArrayList<ErrorCodes>();
        List<String> availFileTypes = getAvailableFileTypes(projectDetails.getFileDetails(), false);

        //Formats provided by customer
        Set<String> availFormats = projectDetails.getFileDetails().stream()
                .filter(fd -> fd.getFormat() != null)
                .filter(fd -> !fd.isSelected())
                .map(FileDetails::getFormat)
                .collect(Collectors.toSet());
        
        availFileTypes.addAll(availFormats);
        
        availFileTypes.forEach(type -> {
            errCodes.add(ErrorCodeMap.getCodeForFileType(type));
        });
        return errCodes;
    }

    /**
     * This method save the details of the projectDetails into the database
     *
     * @param projectDetails Details of the project
     */
    public void save(ProjectDetails projectDetails) {
        if (!projectDetails.isAttachReplace()) {
            //Check that rNumber is given in the JSON request or not.
            if (StringUtils.isEmpty(projectDetails.getrNumber())) {
                projectDetails.getErrors().put("V0000", "rNumber is required");
                return;
            }
            if (StringUtils.isEmpty(projectDetails.getServiceType())) {
                projectDetails.getErrors().put("V0000", "Service Type is required");
                return;
            } else {
                if (!isServiceTypeValid(projectDetails)) {
                    return;
                }
            }
        }
        //Check for both newProject and attach/Replace values
        if ((projectDetails.isNewProject() && projectDetails.isAttachReplace())) {
            projectDetails.getErrors().put("V0016", "Invalid Value of newProject and AttachReplace(Both values cannot be true).");
            return;
        }

        //If projectID/R# is not there, get it from FEMS API call. Stub the call for now
        //Check if new version is required or its an add/replace for existing version.
        String projectId = getProjectId(projectDetails);
        String version = getVersion(projectDetails);
        
        if (StringUtils.isEmpty(version)) {
            //projectDetails.getErrors().put("V0000", "Data not validated/saved as nothing to Attach/Replace. Try submitting again with attachReplace = false");
            //return;
            version = UUIDs.timeBased().toString();
            logger.info("*******INFO: Generating new Version .");
        }
        
        projectDetails.setProjectId(projectId);
        projectDetails.setVersion(version);

        //To process the gerber file,call the processGerber() method
        processGerber(projectDetails.getFileDetails());

        //Save projectFiles
        projectDetails.getFileDetails().stream().forEach(fd -> {
            ProjectFiles pFiles = ReportUtility.convertToDBObject(fd);
            pFiles.setVersion(UUID.fromString(projectDetails.getVersion()));
            pFiles.setProjectId(projectId);
            projectFilesService.save(pFiles);
        });

        //Save into project table               
        projectService.save(ReportUtility.convertToDBObject(projectDetails));
        
    }
    
    public void setIdValidation(ProjectDetails projectDetails) {
        //If setId is not there,then delete the records from project_files or project table.                
        String projectId = projectDetails.getProjectId();
        String version = projectDetails.getVersion();
        projectDetails.getFileDetails().stream().forEach(fd -> {
            fd.setVersion(UUID.fromString(version));
            ProjectFiles pFiles = ReportUtility.convertToDBObject(fd);
            pFiles.setProjectId(projectId);
            projectFilesService.delete(pFiles);
        });
        projectService.delete(ReportUtility.convertToDBObject(projectDetails));
    }
    
    public boolean isServiceTypeValid(ProjectDetails projectDetails) {
        
        if (StringUtils.isEmpty(projectDetails.getServiceType())) {
            projectDetails.getErrors().put("V0000", "Service Type is required");
            return false;
        }
        String[] splitServiceTypes = projectDetails.getServiceType().split(",");
        for (int i = 0; i < splitServiceTypes.length; i++) {
            String splitServiceType = splitServiceTypes[i].toLowerCase().trim();
            if (getServiceId(splitServiceType) == 0) {
                projectDetails.getErrors().put("V0000", "Invalid Service Type - " + splitServiceTypes[i]);
                return false;
            }
        }
        return true;
    }

    /**
     * This method retrieves the projectId of that record from the database
     * while matching the record with customerId or emailAddress or zipFileName
     *
     * @param projectDetails Details of the project
     * @return the projectID of matching record
     */
    private String getProjectId(ProjectDetails projectDetails) {
        
        Map<String, String> projKeyMap = new HashMap<String, String>();
        //If exists in parameter object, return that       
        if (!StringUtils.isEmpty(projectDetails.getProjectId())) {
            return projectDetails.getProjectId();
        }
        //Get by RNumber.
        projKeyMap = getProjectIdByRNumber(projectDetails.getrNumber());

//        //For existing project ( customer forgot to pass projectID, we need to find it)        
//        if (!projectDetails.isNewProject()) {
//            //Get by rNumber 
//            projKeyMap = getProjectIdByRNumber(projectDetails.getrNumber());
//            if (StringUtils.isEmpty(projKeyMap.get("project_id"))) {
//                return null;
//            }
//        }
        if (StringUtils.isEmpty(projKeyMap.get("project_id"))) {
            logger.info("*******INFO: Generating projectID as no data found by RNumber.");
            projKeyMap.put("project_id", UUIDs.timeBased().toString());
        }
        
        if (projectDetails.isAttachReplace()) {
            projectDetails.setVersion(projKeyMap.get("version"));
        }
        return projKeyMap.get("project_id");
    }

    /**
     * Find the project by rNumber
     *
     * @param rNumber the rNumber
     * @return map of projectId and version of matching record
     */
    private Map<String, String> getProjectIdByRNumber(String rNumber) {
        Map<String, String> retMap = new HashMap<String, String>();
        if (!StringUtils.isEmpty(rNumber)) {
            List<ProjectDetails> projDtl = projectService.findByrNumber(rNumber);
            ProjectDetails latestRecord = getLatestRecord(projDtl);
            if (latestRecord != null) {
                retMap.put("project_id", latestRecord.getProjectId());
                retMap.put("version", latestRecord.getVersion());
            }
        }
        return retMap;
    }
    
    public ProjectDetails getProjectByRNumber(String rNumber) {
        ProjectDetails project = null;
        if (!StringUtils.isEmpty(rNumber)) {
            List<ProjectDetails> projDtl = projectService.findByrNumber(rNumber);
            project = getLatestRecord(projDtl);
        }
        return project;
    }

    /**
     * Get the latest record from the list. Based on created date of the record.
     *
     * @param projDtl Details of the project
     * @return Null if not found. Else return the match
     */
    public ProjectDetails getLatestRecord(List<ProjectDetails> projDtl) {
        ProjectDetails latestRecord = new ProjectDetails();
        if (projDtl != null && projDtl.size() > 0) {
            latestRecord = projDtl.stream()
                    .max((a1, a2) -> a1.getCreateDate().compareTo(a2.getCreateDate())).orElse(null);
        }
        return latestRecord;
    }

    /**
     * Get the previous record for the given project. Based on created date
     *
     * @param projDtl Details of the project
     * @return Null if not found. Else return the match
     */
    private ProjectDetails getPreviousRecord(ProjectDetails projDtl) {
        ProjectDetails prevRecord = null;
        
        List<ProjectDetails> allRecords = projectService.findByrNumber(projDtl.getrNumber());//KeyProjectId(projDtl.getProjectId());
        if (allRecords != null) {
            if (projDtl.isAttachReplace() && allRecords.size() == 1) {
                prevRecord = allRecords.stream()
                        .max((a1, a2) -> a1.getCreateDate().compareTo(a2.getCreateDate())).orElse(null);
                return prevRecord;
            }
            // first remove the current record by removing same version record.
            // From the remaining, we will find the latest by created date
            prevRecord = allRecords.stream()
                    .filter(p -> !p.getVersion().equals(projDtl.getVersion()))
                    .max((a1, a2) -> a1.getCreateDate().compareTo(a2.getCreateDate())).orElse(null);
            
        }
        
        return prevRecord;
    }

    /**
     * Find the version for the given project.
     *
     * @param projectDetails
     * @return Create new if doesn't exist in given project
     */
    private String getVersion(ProjectDetails projectDetails) {
        String version = null;
        if (projectDetails.isAttachReplace()) {
            return projectDetails.getVersion();
        } else {
            version = UUIDs.timeBased().toString();
        }
        return version;
    }
     

//    public ProjectDetails returnProjectId(ProjectDetails projectDetails) {
//        //Get the projectDetails by projectId
//        //call validateFiles( ProjectDetails projectDetails ) to get results
//        //Check if new version is required or its an add/replace for existing version.
//        String projectId = getProjectId(projectDetails);
//        String version = getVersion(projectDetails);
//
//        projectDetails.setProjectId(projectId);
//        projectDetails.setVersion(version);
//
//        //To process the gerber file,call the processGerber() method
//        processGerber(projectDetails.getFileDetails());
//        return projectDetails;
//    }
    /**
     * Performs all possible Gerber file processing.
     *
     * @param fileDetails - These given file details will be updated if we find
     * more details during processing
     */
    public void processGerber(List<FileDetails> fileDetails) {
        
        GerberFileProcessingUtil.processFilesByExtension(fileDetails, extensionToFileMap());

        //For each file that is gerber format
        fileDetails.stream()
                .forEach(fd -> {
                    //Apply rules by name pattern
                    GerberFileProcessingUtil.parseFileName(fd);
                });
    }

    //==================================================================================================//
    //============== Below methods are not used but will be required in future. DONOT Delete
    //==================================================================================================//
    /**
     * ODB processing. Mainly parse matrix file to get fileDetils
     *
     * @param folder the path of the folder
     * @param projectId
     * @return the list of fileDetails
     */
    private List<FileDetails> processODB(Path folder, String projectId) {
        //To check that whether file type is ODB or not.
        File[] listOfFiles = folder.toFile().listFiles();
        List<FileDetails> fdList = new ArrayList<FileDetails>();
//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isDirectory()) {
//                if (listOfFiles[i].getName().toLowerCase().equals("odb")) {
//                    Path checkODBFolder = Paths.get(util.getUploadDir() + File.separator + projectId + File.separator + listOfFiles[i].getName() + File.separator + "matrix" + File.separator + "matrix").toAbsolutePath().normalize();
//                    if (checkODBFolder.toFile().exists()) {
//                        fdList = ODBProcessing.processODB(checkODBFolder);
//                        //Print Result 
//                        //fdList.stream().forEach(fd->
//                        //System.out.println(fd.getName()));
//                    }
//                }
//            }
//        }
        return fdList;
    }

	/**
	 * @return the fileUtil
	 */
	public FileStoreUtil getFileUtil() {
		return fileUtil;
	}

	/**
	 * @param fileUtil the fileUtil to set
	 */
	public void setFileUtil(FileStoreUtil fileUtil) {
		this.fileUtil = fileUtil;
	}

    /**
     * Extract and save the zip file. No validations.
     *
     * @param file
     * @param inputs
     * @return
     * @throws IOException
     */
    /**
     * public Set<String> extractAndSaveFiles(MultipartFile file,
     * CustomerInformation inputs) throws IOException { // Local file based
     * String fileName = util.storeFile(inputs.getProjectId(), file);
     * util.extractFiles(inputs.getProjectId(), fileName); //Path folder =
     * Paths.get(util.getUploadDir() + File.separator + inputs.getProjectId() +
     * File.separator).toAbsolutePath().normalize(); // END local	* //S3 Based
     * //util.storeFile(inputs.getProjectId(), file);
     * //report.setExctractedFileNames( util.listObjects(inputs.getProjectId())
     * ); // end S3 return util.listFiles(inputs.getProjectId()); }*
     */
    /**
     * Upload, extract and validates files
     *
     * @param file - the file to be uploaded
     * @param inputs - the inputs of CustomerInputs
     * @return Report - report with validation status
     */
    /**
     * public Report uploadAndExtractFile(MultipartFile file,
     * CustomerInformation inputs, PCBInformation boardInfo) throws Exception {
     *
     * ProjectDetails projectDetails = new ProjectDetails();
     * projectDetails.setProjectId(inputs.getProjectId());
     *
     * Report report = validateFiles(projectDetails);
     *
     * logger.debug("****** Done generating report *******");
     *
     * //To delete the folder Path folder = Paths.get(util.getUploadDir() +
     * File.separator +
     * projectDetails.getProjectId()).toAbsolutePath().normalize();
     * FileUtil.deleteFolder(folder.toFile()); return report; }*
     */
}
