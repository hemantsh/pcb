package com.sc.fe.analyze.controller;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.Report;

@RestController
@RequestMapping(path="/api")
public class AnalyzePackageController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzePackageController.class);
	
	@Autowired
    private FileExtractUploadService fileUploadService;
	
	
	@PostMapping(path="/uploadAndExtract")
	public Report uploadAndAnalyze( @RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) throws IOException {
		System.out.println("Parameters : "+file.getOriginalFilename() + " projectId: "+projectId);
		logger.debug( "Parameters : "+file.getOriginalFilename() + " projectId: "+projectId );
		
		CustomerInputs custInputs = new CustomerInputs();
		custInputs.setProjectId(projectId);
		
		return fileUploadService.uploadAndExtractFile(file, custInputs);
		
	}
}
