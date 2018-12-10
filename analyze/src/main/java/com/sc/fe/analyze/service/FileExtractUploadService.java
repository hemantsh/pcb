package com.sc.fe.analyze.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.FileStorageProperties;
import com.sc.fe.analyze.rules.RuleEngine;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.Report;

import util.FileStoreUtil;
import util.S3FileUtility;

@Service
public class FileExtractUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileExtractUploadService.class);
	private FileStoreUtil util;
	
	//private S3FileUtility util;
	
	@Autowired
	public FileExtractUploadService(FileStorageProperties fileStorageProperties) {
    	this.util = FileStoreUtil.getInstance(fileStorageProperties); //For local file
		//this.util = S3FileUtility.getInstance(fileStorageProperties); //new S3FileUtility(fileStorageProperties); // For S3 storage
    }
	

	public Report uploadAndExtractFile(MultipartFile file, CustomerInputs inputs) throws IOException {
		
		Report report = new Report();
		report.setCustomerInputs(inputs);
		report.setSummary("****** File upload and basic validation by extension. *******");

// Local file based
		String fileName = util.storeFile(inputs.getProjectId(), file);		
		util.extractFiles(inputs.getProjectId(), fileName);   
		report.setExctractedFileNames( util.listFiles(inputs.getProjectId()) );
// END local
		
//S3 Based
//		util.storeFile(inputs.getProjectId(), file);
//		report.setExctractedFileNames( util.listObjects(inputs.getProjectId()) );
// end S3
		
		Map<String, String> extensionToTypeMapping = RuleEngine.getFileTypeExtensionMapping();
		Map<String, Set<String> > filePurposeToNameMapping = new HashMap<String, Set<String> >();
		
		
		report.getExctractedFileNames().forEach( exfile -> {
			String[] nameParts = exfile.split("\\.");
			String extn = nameParts[nameParts.length-1].toLowerCase();
			
        	if(extensionToTypeMapping.containsKey( extn ) ) {
        		
        		Set<String> currentMapping = filePurposeToNameMapping.get(extensionToTypeMapping.get( extn ) );
        		
        		if( currentMapping == null) {
        			currentMapping = new HashSet<String>();
        		}
        		currentMapping.add(exfile);
        		filePurposeToNameMapping.put(extensionToTypeMapping.get( extn ), currentMapping);
        	}
		});
		
		report.setFilePurposeToNameMapping(filePurposeToNameMapping);
		System.out.println("****** Done generating report *******");
		logger.debug("****** Done generating report *******");
		return report;
	}
	
	
}
