package com.sc.fe.analyze.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInputs;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.service.*;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyzePackageController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzePackageController.class);
	
	@Autowired
        private FileExtractUploadService fileUploadService;
        	
	@PostMapping(path="/uploadAndExtract")
	public Report uploadAndAnalyze( @RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) throws Exception {
		System.out.println("Parameters : "+file.getOriginalFilename() + " projectId: "+projectId);
		logger.debug( "Parameters : "+file.getOriginalFilename() + " projectId: "+projectId );
		
		CustomerInputs custInputs = new CustomerInputs();
		custInputs.setProjectId(projectId);
		
		return fileUploadService.uploadAndExtractFile(file, custInputs);
		
	}
	
	@PostMapping(path="/saveReport")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String saveExternalReport(@RequestBody AdvancedReport reqBody) {
		
		return "{\"success\":1}";
	}
	
	@GetMapping(path="/report/{id}")
	@ResponseBody
	public AdvancedReport getReport(@PathParam("id") String id ) {
		AdvancedReport report = new AdvancedReport();
		
		CustomerInputs custInputs = new CustomerInputs();
		custInputs.setProjectId("1234");
		custInputs.setZipFileName("8000-4890.zip");
		custInputs.setZipFileSize("4.6 MB");
		custInputs.setServiceType("Assembly");
		custInputs.setCustomerId("CustId");
		custInputs.setEmailAddress("abc@xyz.com");
		
		report.setCustomerInputs(custInputs);
		
		FileDetails fd = new FileDetails();
		fd.setFileFormat("Gerber");
		fd.setName("xyz/abc/123.gbr");
		
		fd.setFileSize("125 MB");
		fd.setModifiedDate(new Date());
		
		report.addFileDetail(fd);
		
		return report;
	}
	
	
	@GetMapping(path="/allFileTypes")
	@ResponseBody
	public List<FileTypes> getAllFilesTypes() {
		List<FileTypes> types = new ArrayList<FileTypes>();
		
		return types;
	}
	
	@GetMapping(path="/getServiceFiles/{serviceId}")
	@ResponseBody
	public List<FileTypes> getServiceFiles() {
		List<FileTypes> types = new ArrayList<FileTypes>();
		
		return types;
	}
}
