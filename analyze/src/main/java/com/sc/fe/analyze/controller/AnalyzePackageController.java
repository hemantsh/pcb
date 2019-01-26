package com.sc.fe.analyze.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sc.fe.analyze.to.CustomerInformation;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.PCBInformation;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.to.TurnTimeQuantity;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyzePackageController {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzePackageController.class);
	
	@Autowired
        private FileExtractUploadService fileUploadService;
        	
	@PostMapping(path="/uploadAndExtract")
	public AdvancedReport uploadAndAnalyze( @RequestParam("file") MultipartFile file, @RequestParam("projectId") String projectId) throws Exception {
                             
		System.out.println("Parameters : "+file.getOriginalFilename() + "   ProjectId: "+projectId);
		logger.debug( "Parameters : "+file.getOriginalFilename() + " projectId: "+projectId );
		
		CustomerInformation custInputs = new CustomerInformation();
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
		
		CustomerInformation custInputs = new CustomerInformation();
		custInputs.setProjectId("1234");
		custInputs.setCustomerId("CustId");
		custInputs.setEmailAddress("abc@xyz.com");
		report.setCustomerInformation( custInputs );
		
		PCBInformation pcbInfo = new PCBInformation();
		
		pcbInfo.setZipFileName("8000-4890.zip");
		pcbInfo.setZipFileSize("4.6 MB");
		pcbInfo.setServiceType("Assembly");	
		pcbInfo.setBoardType("Flexi");
		pcbInfo.setLayers(8);
		pcbInfo.setPcbClass("Class 2");		
		pcbInfo.addTurnTimeQuantity(new TurnTimeQuantity(5, 100));
		report.setBoardInfo(pcbInfo);
		
		FileDetails fd = new FileDetails();
		fd.setFormat("Gerber");
		fd.setName("xyz/abc/123.gbr");
		fd.setFileSize("125 MB");
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
