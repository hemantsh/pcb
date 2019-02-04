package com.sc.fe.analyze.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.PCBInformation;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.util.ErrorCodeMap;
import com.sc.fe.analyze.util.ErrorCodes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/fm")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value="FileManagementController",produces=MediaType.APPLICATION_JSON_VALUE)

public class FileManagementController {

	private static final Logger logger = LoggerFactory.getLogger(FileManagementController.class);
	
	@Autowired
    private FileExtractUploadService fileUploadService;
    
	
	@PostMapping(path="/validate")
	@ResponseBody
	public Map<String,String> validate(@RequestBody ProjectDetails projectDetails) {
		
		Map<String,String> retMap = new HashMap<String,String>();
		
		Report report = fileUploadService.validateFiles(projectDetails);
		if( report != null && report.getErrorCodes() != null) {
			
			report.getErrorCodes().stream().forEach( err -> {
				retMap.put( err.toString(), err.getErrorMessage());
			});
		}
		
		return retMap;
	}
	
	@PostMapping(path="/validateAndSave")
	@ResponseBody
	public Map<String,String> validateAndSave(@RequestBody ProjectDetails projectDetails) {
		
		fileUploadService.save(projectDetails);
		return validate(projectDetails);
	}
	
	
	
	
}
