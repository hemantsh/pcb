package com.sc.fe.analyze.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.to.AdvancedReport;
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.PCBInformation;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.to.TurnTimeQuantity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value="AnalyzePackageController",produces=MediaType.APPLICATION_JSON_VALUE)

public class AnalyzePackageController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzePackageController.class);
	
	@Autowired
    private FileExtractUploadService fileUploadService;
        	
	@PostMapping(path="/uploadAndValidate")
    @ApiOperation("Uploaded file gets Analyzed and Extracted")
	public Report uploadAndAnalyze ( 
			@ApiParam("Takes ZIP file as Input") @RequestParam("file") MultipartFile file, 
			@ApiParam("ProjectId i.e. Folder Name in which zip file gets extracted.") @RequestParam("projectId") String projectId,
			@RequestParam("serviceType") String serviceType ) throws Exception {
                             
		System.out.println("Parameters : "+file.getOriginalFilename() + "   ProjectId: "+projectId);
		logger.debug( "Parameters : "+file.getOriginalFilename() + " projectId: "+projectId );
		
		CustomerInformation custInputs = new CustomerInformation();
		custInputs.setProjectId(projectId);
		
		PCBInformation boardInfo = new PCBInformation();
		boardInfo.setServiceType(serviceType);
		                             
		return fileUploadService.uploadAndExtractFile(file, custInputs, boardInfo);	
	}
	
	@PostMapping(path="/uploadAndSave")
	public Set<String> uploadProjectFiles(
			@RequestParam("file") MultipartFile file, 
			@RequestParam("projectId") String projectId ) throws IOException {
		
		CustomerInformation inputs = new CustomerInformation();
		inputs.setProjectId(projectId);
		
		return fileUploadService.extractAndSaveFiles(file, inputs );
	}
	
	@PostMapping(path="/validate")
	@ResponseBody
	public Report validate(@RequestBody ProjectDetails projectDetails) {
		
		 return fileUploadService.validateFiles(projectDetails);
	}
	
	@PostMapping(path="/saveReport")
    @ApiOperation("Generates a report and store the data into database.")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public String saveExternalReport(@ApiParam("Takes JSON of Report Object as Input") @RequestBody AdvancedReport reqBody) {
		
		return "{\"success\":1}";
	}
	
	@GetMapping(path="/project/{id}/report")
    @ApiOperation("Sets the customer inputs and generates the report.")
	@ResponseBody

	public Report getReport(@ApiParam("Takes ProjectId as Input") @PathParam("id") String id ) {
		
		Report report = new Report();
		ProjectDetails projectDetail = new ProjectDetails();
		
		CustomerInformation custInputs = new CustomerInformation();
		
		custInputs.setCustomerId("CustId");
		custInputs.setEmailAddress("abc@xyz.com");
		
		projectDetail.setCustomerInformation( custInputs );
		projectDetail.setProjectId("1234");
		
		PCBInformation pcbInfo = new PCBInformation();
		
		pcbInfo.setZipFileName("8000-4890.zip");
		pcbInfo.setZipFileSize("4.6 MB");
		pcbInfo.setServiceType("Assembly");	
		pcbInfo.setBoardType("Flexi");
		pcbInfo.setLayers(8);
		pcbInfo.setPcbClass("Class 2");		
		pcbInfo.addTurnTimeQuantity(new TurnTimeQuantity(5, 100));
		projectDetail.setBoardInfo(pcbInfo);
		
		FileDetails fd = new FileDetails();
		fd.setFormat("Gerber");
		fd.setName("xyz/abc/123.gbr");
		fd.setSize("125 MB");
		fd.setModifiedDate("MM-dd-yyyy HH:mm:ss");
		fd.setCopperWeight(".95");
		fd.setLayerOrder(1);
		fd.setPolarity("Positive");
		fd.setEndName("endName");
		fd.setStartName("startName");
		fd.setType("solder_mask");
		fd.setSide("top");
		fd.setStep("step");
		fd.setContext("BOARD");
		
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("out_mirror", "no");
		attributes.put("lpol_done", "no");
		attributes.put("cu_base", "no");
		attributes.put("comment", "");
		attributes.put("inp_file", "");
		attributes.put("eda_layers", "BottomPaste");
		attributes.put("out_angle", "0.0");
		attributes.put("out_polarity", "positive");
		attributes.put("layer_hdi_type", "buildup");
		attributes.put("out_x_scale", "1.000000");
		attributes.put("out_y_scale", "1.000000");
		attributes.put("out_comp", "0.000000");
		attributes.put("et_adjacency", "20.000000");
		attributes.put("layer_dielectric", "0.0001");
		attributes.put("copper_weight", "1");
		
		fd.setAttributes(attributes);
		
		projectDetail.addFileDetail(fd);
		
		report.setProjectDetail(projectDetail);
		
		return report;
	}
	
	
}
