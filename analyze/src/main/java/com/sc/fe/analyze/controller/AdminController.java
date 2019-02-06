/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.controller;

import com.sc.fe.analyze.data.entity.ExtensionFileType;
import com.sc.fe.analyze.data.entity.Extensions;
import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.service.ExtensionFileService;
import com.sc.fe.analyze.service.FileExtensionService;
import com.sc.fe.analyze.service.FileServices;
import com.sc.fe.analyze.service.FileTypeService;
import com.sc.fe.analyze.service.ReportServices;
import com.sc.fe.analyze.service.ServiceFilesServices;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.util.MappingUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pc
 */
@RestController
@RequestMapping(path="/admin")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value="AdministrationController",produces=MediaType.APPLICATION_JSON_VALUE)
public class AdminController {
    @Autowired
    private FileExtensionService fileExtnServ;
    @Autowired
    private FileServices fileService;
    @Autowired
    private FileTypeService fileTypeService;
    @Autowired
    private ServiceFilesServices serviceFileservice;
    @Autowired
    private ReportServices reportServices;
    @Autowired
    private ExtensionFileService extnFileService;
    
    @GetMapping(path="/project/{projectId}/version/{version}")
	@ResponseBody
	public ProjectDetails getProjectDetails( @PathParam("projectId") String projectId, @PathParam("version") String version ) {
		ProjectDetails project = new ProjectDetails();
		//TODO user service/repo classes to get project and project_file data, 
		//convert and combine to create ProjectDetails object
		return project;
	}
	
	@GetMapping(path="/projects")
	@ResponseBody
	public List<ProjectDetails> getAllProjects(  ) {
		List<ProjectDetails> projectList = new ArrayList<ProjectDetails>();
		//TODO user service/repo classes to get project data. repo.getAll()
		//Here we will not read project_files table
		return projectList;
	}
	
    //Extension Services
    @ApiOperation("Retrieve all the Extensions from the Database.")
    @GetMapping(path="/extensions")
    public List<Extensions> getAllExtensions(){
        return  fileExtnServ.findAll();
    }
    @ApiOperation("Insert a new Extension and store into Database.")
    @PostMapping(path="/extensions/create")
    public void createExtensions(@ApiParam("Takes JSON of Extension Type as Input") @RequestBody Extensions extension){
         fileExtnServ.save(extension);
    }
    @ApiOperation("Update existing Extension stored into the Database.")
    @PostMapping(path="/extensions/update")
    public void updateExtensions(@ApiParam("Takes JSON of Extension as Input") @RequestBody Extensions extension){
         fileExtnServ.save(extension);
    }
    @ApiOperation("Retrieve the Extension By Id from the Database.")
    @GetMapping(path="/extensions/retrive/{id}")
    public Extensions retriveExtensions(@ApiParam("Takes Extension Id as Input") @PathVariable("id") int extensionId){
         return fileExtnServ.getExtensionById(extensionId);
    }
    
    
    
    //Service Services
    @ApiOperation("Retrieve all the Services from the Database.")
    @GetMapping(path="/services")
    public List<Services> getAllServices(){
        return fileService.findAll();
    }
    @ApiOperation("Insert a new Services and store into Database.")
    @PostMapping(path="/services/create")
    public void createService(@ApiParam("Takes JSON of Services Type as Input") @RequestBody Services services){
        fileService.save(services);
    }
    @ApiOperation("Updates the existing Service stored into Database.")
    @PostMapping(path="/services/update")
    public void updateService(@ApiParam("Takes JSON of Services as Input") @RequestBody Services services){
        fileService.save(services);
    }
    @ApiOperation("Retrieve the Services By Id from the Database")
    @GetMapping(path="/services/retrive/{id}")
    public Services retriveServices(@ApiParam("Takes Service Id as Input") @PathVariable("id") int serviceId ){
    return fileService.getServicesById(serviceId);
    }
    
    //FileType Services
    @ApiOperation("Retrieve all the Filetypes from the Database.")
    @GetMapping(path="/filetypes")
    public List<FileTypes> getAllFileTypes(){
        return fileTypeService.findAll();
    }
    @ApiOperation("Insert a new Filetype and store into Database.")
    @PostMapping(path="/filetypes/create")
    public void createFileType(@ApiParam("Takes JSON of Filetype as Input") @RequestBody FileTypes filetype){
    fileTypeService.save(filetype);
    }
    @ApiOperation("Updates the existing Filetype stored into Database.")
    @PostMapping(path="/filetypes/update")
    public void updateFileType(@ApiParam("Takes JSON of Filetype as Input") @RequestBody FileTypes filetype){
    fileTypeService.save(filetype);
    }
    @ApiOperation("Retrieve the Filetype By Id from the Database")
    @GetMapping(path="/filetypes/retrive/{id}")
    public FileTypes retriveFileType(@ApiParam("Takes filetypeId as Input") @PathVariable("id")int filetypeId){
        return fileTypeService.getTypeById(filetypeId);
    }
    
    //ServiceFiles services
    @ApiOperation("Retrieve all the Service To Files Mapping from the Database.")
    @GetMapping(path="/servicefiles")
    public List<ServiceFiles> getAllServiceFile()
    {
        return serviceFileservice.findAll();
    }
    @ApiOperation("Insert a new Service To Files Mapping and store into Database.")
    @PostMapping(path="/servicefiles/create")
    public void createServiceFiles(@ApiParam("Takes JSON of ServiceFiles as Input") @RequestBody ServiceFiles serviceFiles){
    serviceFileservice.save(serviceFiles);
    }
    @ApiOperation("Inserts an Array of Service To Files Mapping and store into Database.")
    @PostMapping(path="/servicefiles/createmulti")
    public void createServiceFilesAll(@ApiParam("Takes Array JSON of ServiceFiles Mapping") @RequestBody List<ServiceFiles> serviceFiles){
    serviceFileservice.saveAll(serviceFiles);
    }
    @ApiOperation("Updates the existing Service To Files Mapping stored into Database.")
    @PostMapping(path="/servicefiles/update")
    public void updateServiceFiles(@ApiParam("Takes JSON of ServiceFiles as Input") @RequestBody ServiceFiles serviceFiles){
    serviceFileservice.save(serviceFiles);
    }
    @ApiOperation("Retrieve the Service_Files By Id from the Database")
    @GetMapping(path="/servicefiles/retrive/{id}")
    public List<ServiceFiles> retriveServiceFiles(@ApiParam("Takes Service Id as Input") @PathVariable("id")int service_id){
        return serviceFileservice.getFilesByService(service_id);
    }
    
    //ExtensionFileServices
    @ApiOperation("Retrieve all the Extension To Files Mapping from the Database.")
    @GetMapping(path="/extensionfiles")
    public List<ExtensionFileType> getExtensionFiles(){
        return extnFileService.findAll();
    }
    @ApiOperation("Map a new Extensions To File and store into Database.")
    @PostMapping(path="/extensionfiles/create")
    public void createExtensionFiles(@ApiParam("Takes JSON of ExtensionFiles Mapping") @RequestBody ExtensionFileType extensionfileType){
         extnFileService.save(extensionfileType);
    }
    @ApiOperation("Inserts an Array of Extension To Files Mapping and store into Database.")
    @PostMapping(path="/extensionfiles/createmulti")
    public void createExtensionFilesAll(@ApiParam("Takes Array JSON of ExtensionFiles Mapping") @RequestBody List<ExtensionFileType> extensionfileType){
         extnFileService.saveAll(extensionfileType);
    }
    @ApiOperation("Updates the existing Extension To Files Mapping stored into Database.")
    @PostMapping(path="/extensionfiles/update")
    public void updateExtensionFiles(@ApiParam("Takes JSON of ExtensionFiles Mapping") @RequestBody ExtensionFileType extensionfileType){
        extnFileService.save(extensionfileType);
    }
    @ApiOperation("Retrieve the Extension To Files Mapping By Id from the Database")
    @GetMapping(path="/extensionfiles/retrive/{id}")
    public List<ExtensionFileType> getExtensionFilesById(@ApiParam("Takes Extension Id as Input") @PathVariable("id") int extensionId){
        List<ExtensionFileType> temp=extnFileService.getExtenFileTypeById(extensionId);
        return temp;
    }
    
    //Report Services
    @ApiOperation("Retrieve all the Report from the Database.")
    @GetMapping(path="/report")
    public List<Report> getAllReport(){
        return reportServices.findAll();
    }
    @ApiOperation("Generates Report and store into the Database.")
    @PostMapping(path="/report/create")
    public void createReport(@ApiParam("Takes JSON of Report Object") @RequestBody Report report){
       reportServices.save(report);
    }
    @ApiOperation("Retrieve the Report by Id from the Database.")
    @GetMapping(path="/report/{id}")
    public List<Report> getReportById(@PathVariable("id") String projectId){
        return reportServices.getReportById(projectId);
    }
    @ApiOperation("Updates Report already stored into the Database.")
    @PostMapping(path="/report/update")
    public void updateReport(@ApiParam("Takes JSON of Report Object to update") @RequestBody Report report){
       reportServices.save(report);
    }
    @ApiOperation("Retrieve Filetype By fileId from the Database.")
    @GetMapping(path="/report/getfiletype/{fileId}")
    @ResponseBody
    public String getFiletype(@PathVariable("fileId") Integer fileId){
        return reportServices.getFileType(fileId);
    }
}
