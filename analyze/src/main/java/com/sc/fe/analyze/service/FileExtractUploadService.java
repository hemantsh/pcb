package com.sc.fe.analyze.service;

import java.io.File;
import java.io.IOException;
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
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.util.FileStoreUtil;
import com.sc.fe.analyze.util.GerberFileProcessingUtil;
import com.sc.fe.analyze.util.MappingUtil;
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
	

	public Report uploadAndExtractFile(MultipartFile file, CustomerInputs inputs) throws IOException {
		
// Local file based
		String fileName = util.storeFile(inputs.getProjectId(), file);		
		util.extractFiles(inputs.getProjectId(), fileName);   	
// END local
		
//S3 Based
//		util.storeFile(inputs.getProjectId(), file);
//		report.setExctractedFileNames( util.listObjects(inputs.getProjectId()) );
// end S3
		
		Report report = new Report();
		report.setCustomerInputs(inputs);
		report.setSummary("****** File upload and basic validation by extension. *******");
		inputs.setServiceType("Assembly");
		report.setExctractedFileNames( util.listFiles(inputs.getProjectId()) );
		
		List<String> requiredFiles = baseService.getServiceFiles( MappingUtil.getServiceId(inputs.getServiceType()));
		Set<String> foundFiles = new HashSet<String>();
		
		Map<String, Set<String> > filePurposeToNameMapping = GerberFileProcessingUtil.processFilesByExtension(report, 
				baseService.getExtensionToFileMapping(), 
				foundFiles);
		
		if(requiredFiles.size() != foundFiles.size()) {
			report.setValidationStatus("Invalid design.");
			report.addError("Some required files are missing for the selected service.");
			requiredFiles.removeAll(foundFiles);
			//report.addAdditionalNote( "Following are the missing files in the package." );
			requiredFiles.stream().forEach( missedFile -> {
				report.addAdditionalNote( missedFile + " file missing");
			});
		}else {
			report.setValidationStatus("Good design with all required files.");
		}
		
		report.setFilePurposeToNameMapping(filePurposeToNameMapping);
		
		reportRepo.insert(ReportUtility.convertToDBObject(report));
		
		System.out.println("****** Done generating report *******");
		logger.debug("****** Done generating report *******");
		
		String folder = util.getUploadDir() + File.separator + report.getCustomerInputs().getProjectId() + File.separator;
		
		FileDetails fd = GerberFileProcessingUtil.processFile(folder+"8000-4890CPWIZA-SquareHoles.TXT", 
				baseService.getExtensionToFileMapping());
		
		System.out.println("Drill file properties: "+ fd.getAttributes());

		return report;
	}
	
}
