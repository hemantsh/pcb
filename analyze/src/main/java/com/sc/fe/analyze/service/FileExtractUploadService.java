package com.sc.fe.analyze.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.FileStorageProperties;
import com.sc.fe.analyze.data.repo.ReportRepo;
import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.util.FileStoreUtil;
import com.sc.fe.analyze.util.GerberFileProcessingUtil;
import com.sc.fe.analyze.util.MappingUtil;
import com.sc.fe.analyze.util.ODBProcessing;
import com.sc.fe.analyze.util.ReportUtility;

@Service
public class FileExtractUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileExtractUploadService.class);
	private FileStoreUtil util;
	
	//private S3FileUtility util;
	@Autowired
	BaseService baseService;
	@Autowired
	ReportRepo reportRepo;
	
	@Autowired
	public FileExtractUploadService(FileStorageProperties fileStorageProperties) {
    	this.util = FileStoreUtil.getInstance(fileStorageProperties); //For local file
    	//this.util = S3FileUtility.getInstance(fileStorageProperties); 	
    }
	

	public AdvancedReport uploadAndExtractFile(MultipartFile file, CustomerInputs inputs) throws Exception {
		AdvancedReport report = new AdvancedReport();
// Local file based
		String fileName = util.storeFile(inputs.getProjectId(), file);
        util.extractFiles(inputs.getProjectId(), fileName);   
        Path folder = Paths.get(util.getUploadDir() + File.separator + inputs.getProjectId() + File.separator).toAbsolutePath().normalize();
 // END local		
//S3 Based
//		util.storeFile(inputs.getProjectId(), file);
//		report.setExctractedFileNames( util.listObjects(inputs.getProjectId()) );
// end S3
		
		report.setCustomerInputs(inputs);
		report.setSummary("****** File upload and basic validation by extension. *******");
		inputs.setServiceType("Assembly");
		report.setExctractedFileNames( util.listFiles(inputs.getProjectId()) );
                
        List<String> requiredFiles = baseService.getServiceFiles( MappingUtil.getServiceId(inputs.getServiceType()));
        Set<String> foundFiles = new HashSet<String>();
		
		Map<String, Set<String> > filePurposeToNameMapping = GerberFileProcessingUtil.processFilesByExtension(report, 
				baseService.getExtensionToFileMapping(), 
				foundFiles);
                
		List<FileDetails> fileDetails = GerberFileProcessingUtil.extractFileDetails(report, 
				baseService.getExtensionToFileMapping(), folder);
		
		report.setFileDetails(fileDetails);
                
		if(requiredFiles.size() != foundFiles.size()) {
			report.setValidationStatus("Invalid design.");
			report.addError("Some required files are missing for the selected service.");
			requiredFiles.removeAll(foundFiles);
			
			requiredFiles.stream().forEach( missedFile -> {
				report.addAdditionalNote( missedFile + " file missing");
			});
		}else {
			report.setValidationStatus("All required files are found.");
		}
                
        List<FileDetails> fdList = ODBProcessing.processODB(folder);
        report.getFileDetails().addAll(fdList);
                
        report.setFilePurposeToNameMapping(filePurposeToNameMapping);
        reportRepo.insert(ReportUtility.convertToDBObject(report));

		System.out.println("****** Done generating report *******");
		logger.debug("****** Done generating report *******");

		return report;
	}
	
}
