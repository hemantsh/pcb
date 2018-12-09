package com.sc.fe.analyze.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	

	//private FileStoreUtil util;
	
	private S3FileUtility util;
	
	@Autowired
	public FileExtractUploadService(FileStorageProperties fileStorageProperties) {
    	//this.util = new FileStoreUtil(fileStorageProperties); //For local file
		this.util = new S3FileUtility(fileStorageProperties); // For S3 storage
    }
	

	public Report uploadAndExtractFile(MultipartFile file, CustomerInputs inputs) throws IOException {
		
		Report report = new Report();
		report.setCustomerInputs(inputs);
		report.setSummary("****** File upload and basic validation by extension. *******");

// Local file based
//		String fileName = util.storeFile(inputs.getProjectId(), file);		
//		util.extractFiles(inputs.getProjectId(), fileName);   
//		report.setExctractedFileNames( util.listFiles(inputs.getProjectId()) );
// END local
		
//S3 Based
		util.storeFile(inputs.getProjectId(), file);
		report.setExctractedFileNames( util.listObjects(inputs.getProjectId()) );
// end S3
		
		Map<String, String> extensionToTypeMapping = RuleEngine.getFileTypeExtensionMapping();
		Map<String, Set<String> > filePurposeToNameMapping = new HashMap<String, Set<String> >();
		
		
		report.getExctractedFileNames().forEach( exfile -> {
			String[] nameParts = exfile.split("\\.");
        	if(extensionToTypeMapping.containsKey(nameParts[nameParts.length-1].toLowerCase()) ) {
        		
        		Set<String> currentMapping = filePurposeToNameMapping.get(extensionToTypeMapping.get( nameParts[nameParts.length-1].toLowerCase() ) );
        		
        		if( currentMapping == null) {
        			currentMapping = new HashSet<String>();
        		}
        		currentMapping.add(exfile);
        		filePurposeToNameMapping.put(extensionToTypeMapping.get( nameParts[nameParts.length-1].toLowerCase() ), currentMapping);
        	}
		});
		
		report.setFilePurposeToNameMapping(filePurposeToNameMapping);
		System.out.println(util.getFileStorageProperties().getBucket());
		System.out.println(util.getFileStorageProperties().getSecretKey());
		System.out.println(util.getFileStorageProperties().getAccessKey());
		
		return report;
	}
	
	
	
}
