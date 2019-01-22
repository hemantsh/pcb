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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value="AnalyzePackageController",produces=MediaType.APPLICATION_JSON_VALUE)
public class AnalyzePackageController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzePackageController.class);
	
	@Autowired
        private FileExtractUploadService fileUploadService;
        	
	@PostMapping(path="/uploadAndExtract")
        @ApiOperation("Uploaded file gets Analyzed and Extracted")
	public AdvancedReport uploadAndAnalyze( @ApiParam("Takes ZIP file as Input") @RequestParam("file") MultipartFile file, @ApiParam("ProjectId i.e. Folder Name in which zip file gets extracted.") @RequestParam("projectId") String projectId) throws Exception {
                             
		System.out.println("Parameters : "+file.getOriginalFilename() + "   ProjectId: "+projectId);
		logger.debug( "Parameters : "+file.getOriginalFilename() + " projectId: "+projectId );
		
		CustomerInputs custInputs = new CustomerInputs();
		custInputs.setProjectId(projectId);
		                             
		return fileUploadService.uploadAndExtractFile(file, custInputs);
		
	}
	
	@PostMapping(path="/saveReport")
        @ApiOperation("Generates a report and store the data into database.")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String saveExternalReport(@ApiParam("Takes JSON of Report Object as Input") @RequestBody AdvancedReport reqBody) {
		
		return "{\"success\":1}";
	}
	
	@GetMapping(path="/report/{id}")
        @ApiOperation("Sets the customer inputs and generates the report.")
	@ResponseBody
	public AdvancedReport getReport(@ApiParam("Takes ProjectId as Input") @PathParam("id") String id ) {
		AdvancedReport report = new AdvancedReport();
		
		CustomerInputs custInputs = new CustomerInputs();
		custInputs.setProjectId("1234");
		custInputs.setZipFileName("8000-4890.zip");
		custInputs.setZipFileSize("4.6 MB");
		custInputs.setServiceType("Assembly");
		custInputs.setCustomerId("CustId");
		custInputs.setEmailAddress("abc@xyz.com");
		custInputs.setQuantity(100);
		custInputs.setTurnTime(5);
		
		report.setCustomerInputs(custInputs);
		
		FileDetails fd = new FileDetails();
		fd.setFileFormat("Gerber");
		fd.setName("xyz/abc/123.gbr");
		
		fd.setFileSize("125 MB");
		fd.setModifiedDate(new Date());
		
		fd.setCopperWeight(".95");
		fd.setLayerSequence(1);
		fd.setEndName("endName");
		fd.setStartName("startName");
		
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
