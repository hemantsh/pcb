package com.sc.fe.analyze.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path="/fm")
@CrossOrigin(origins = "*")
@Api(value="FileManagementController",produces=MediaType.APPLICATION_JSON_VALUE)

public class FileManagementController {

	private static final Logger logger = LoggerFactory.getLogger(FileManagementController.class);
	
	@Autowired
    private FileExtractUploadService fileUploadService;
    
	
	@PostMapping(path="/validate")
	@ResponseBody
	public ProjectDetails validate(@RequestBody ProjectDetails projectDetails) {
		
		Report report = fileUploadService.validateFiles(projectDetails);
		projectDetails = report.getProjectDetail();
		
		ProjectDetails temp = new ProjectDetails();
        temp.setProjectId(projectDetails.getProjectId());
        temp.setSetId( projectDetails.getSetId());
        temp.setLayers( projectDetails.getLayers());
        temp.setItar( projectDetails.getItar());
        temp.setNofly(  projectDetails.isNofly());
        temp.setNewProject( projectDetails.isNewProject() );
        //temp.setTurnTimeQuantity( projectDetails.getTurnTimeQuantity() );
        temp.setVersion( projectDetails.getVersion());
        temp.setErrors( projectDetails.getErrors() );
        temp.setDifferences( projectDetails.getDifferences() );
        
        
		return temp;
	}
	
	@PostMapping(path="/validateAndSave")
	@ResponseBody
	public ProjectDetails validateAndSave(@RequestBody ProjectDetails projectDetails) {
		
		//ProjectDetails validationResult = validate(projectDetails);
		fileUploadService.save(projectDetails);
		return validate(projectDetails);
	}
	
	
	
	
}
