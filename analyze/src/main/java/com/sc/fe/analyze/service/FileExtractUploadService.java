package com.sc.fe.analyze.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

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
		
		Map<String, Set<String> > filePurposeToNameMapping = processFilesByExtension(report, 
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
		
		//TODO: 
		AdvancedReport advReport = new AdvancedReport();
		advReport.setCustomerInputs(inputs);
		createAdvancedReport(advReport,
				baseService.getExtensionToFileMapping(),
				util.listFiles(inputs.getProjectId()) 
				);
		
		return report;
	}


	private Map<String, Set<String>> processFilesByExtension(Report report, 
			Map<String, String> extensionToFileMapping,
			Set<String> foundFiles) {
		
		Map<String, Set<String>> filePurposeToNameMapping = new HashMap<String, Set<String>>();
		
		report.getExctractedFileNames().forEach( exfile -> {
			
			String[] nameParts = exfile.split("\\.");
			String extn = nameParts[nameParts.length-1].toLowerCase();
			
        	if(extensionToFileMapping.containsKey( extn ) ) {
        		
        		Set<String> currentMapping = filePurposeToNameMapping.get(extensionToFileMapping.get( extn ) );
       		
        		if( currentMapping == null) {
        			currentMapping = new HashSet<String>();
        		}
        		currentMapping.add(exfile);
        		String fileType = extensionToFileMapping.get( extn );
        		filePurposeToNameMapping.put(fileType, currentMapping);
        		foundFiles.add( fileType );
        		
        	}
		});
		return filePurposeToNameMapping;
	}
	
	private void createAdvancedReport(AdvancedReport report, 
			Map<String, String> extensionToFileMapping,
			Set<String> allFiles) {
		
		allFiles.forEach( exfile -> {
			
			String[] nameParts = exfile.split("\\.");
			String extn = nameParts[nameParts.length-1].toLowerCase();
			
        	if(extensionToFileMapping.containsKey( extn ) && ! extn.toLowerCase().equals("pdf")) {
    				
        		//TODO: Now we have found the file that we are interested in, 
        		//we will proecess it line by line to get attributes from our utility
        		FileDetails fileDet = new FileDetails();
        		fileDet.setName(exfile);
        		
        		String folder = util.getUploadDirectory() + File.separator + report.getCustomerInputs().getProjectId() + File.separator;
        		
        		Map<String, String> results = new HashMap<String, String>();
        		try (
        			Stream<String> stream = Files.lines(Paths.get(folder+exfile))) { //You may need to correct path to the absolute path
        			System.out.println(exfile); 
        			stream.forEach( line -> {
        	        	results.putAll( GerberFileProcessingUtil.processLine(line) );
        	        });
        		} catch (IOException e) {
					e.printStackTrace();
				}
        		fileDet.setAttributes(results);
        		report.addFileDetail(fileDet);
        		
        	}
		});
		
	}
	
	//This is the method to process given file
	// exFile must be the full qualified path of the file
	private FileDetails processFile(String exfile) {
		
		String[] nameParts = exfile.split("\\.");
		String extn = nameParts[nameParts.length-1].toLowerCase();
		
		Map<String, String> extensionToFileMapping = baseService.getExtensionToFileMapping();
		
		Map<String, String> flagMap = new HashMap<String, String>();
		flagMap.put("isDrillFile", "N");
		flagMap.put("currentKey", "");
		
		FileDetails fileDet = new FileDetails();
		
    	if(extensionToFileMapping.containsKey( extn ) && ! extn.toLowerCase().equals("pdf")) {
				
    		//TODO: Now we have found the file that we are interested in, 
    		//we will proecess it line by line to get attributes from our utility
    		
    		fileDet.setName(exfile);
    		
    		//Results for the whole file
    		Map<String, String> results = new HashMap<String, String>();
    		
    		try (
    			Stream<String> stream = Files.lines(Paths.get(exfile))) { 
    			
    			stream.forEach( line -> {
    				
    				String currentKey = flagMap.get("currentKey");
    				if( line.startsWith("M48") ) {
    					flagMap.put("isDrillFile", "Y");
    				}
    				//Values for this line only
    				Map<String, String> lineKeyVal = new HashMap<String, String>();
    				
    				if( "Y".equals(flagMap.get("isDrillFile")) ) {
    					currentKey = GerberFileProcessingUtil.processM48(line, results, currentKey );
    					flagMap.put("currentKey", currentKey);
    					
    				}else {
    					lineKeyVal = GerberFileProcessingUtil.processLine(line);
    					results.putAll( lineKeyVal );
    				}
    	        	
    	        });
    		} catch (IOException e) {
				e.printStackTrace();
			}
    		fileDet.setAttributes(results);
    		
    	}
    	
    	return fileDet;
	}
	
}
