package com.sc.fe.analyze.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.to.CustomerInputs;

@RestController
@RequestMapping(path="/api")
public class AnalyzePackageController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzePackageController.class);
	
	@PostMapping(path="/upload")
	public @ResponseBody void uploadAndAnalyze( @RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) {
		System.out.println("Parameters : "+file.getOriginalFilename() + " projectId: "+projectId);
		logger.debug("Parameters : "+file.getName() );
		
		CustomerInputs custInputs = new CustomerInputs();
		custInputs.setProjectId(projectId);
	}
}
