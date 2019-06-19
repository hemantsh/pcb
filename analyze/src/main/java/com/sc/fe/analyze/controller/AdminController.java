/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.controller;

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

//import com.sc.fe.analyze.data.entity.ExtensionFileType;
//import com.sc.fe.analyze.data.entity.Extensions;
//import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.service.BaseService;
import com.sc.fe.analyze.service.ExtensionFileService;
import com.sc.fe.analyze.service.FileExtensionService;
import com.sc.fe.analyze.service.FileServices;
//import com.sc.fe.analyze.service.FileTypeService;
import com.sc.fe.analyze.service.FiletypeExtensionsService;
//import com.sc.fe.analyze.service.ServiceFilesServices;
import com.sc.fe.analyze.service.ServiceFiletypesService;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.to.ProjectDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Hemant
 */
@RestController
@RequestMapping(path = "/admin")
@CrossOrigin(origins = "*")
@Api(value = "AdministrationController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    private FileExtensionService fileExtnServ;
    @Autowired
    private FileServices fileService;
//    @Autowired
//    private FileTypeService fileTypeService;
//    @Autowired
//    private ServiceFilesServices serviceFileservice;
    @Autowired
    private ExtensionFileService extnFileService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private FiletypeExtensionsService filetypeExtensionService;
    @Autowired
    private ServiceFiletypesService serviceFiletypesService;

    @GetMapping(path = "/project/{projectId}/version/{version}")
    @ResponseBody
    public ProjectDetails getProjectDetails(@PathParam("projectId") String projectId, @PathParam("version") String version) {
        ProjectDetails project = new ProjectDetails();
        //TODO user service/repo classes to get project and project_file data, 
        //convert and combine to create ProjectDetails object
        return project;
    }

    @GetMapping(path = "/projects")
    @ResponseBody
    public List<ProjectDetails> getAllProjects() {
        List<ProjectDetails> projectList = new ArrayList<ProjectDetails>();
        //TODO user service/repo classes to get project data. repo.getAll()
        //Here we will not read project_files table
        return projectList;
    }

    //Extension Services
//    @ApiOperation("Retrieve all the Extensions from the Database.")
//    @GetMapping(path = "/extensions")
//    public List<Extensions> getAllExtensions() {
//        return fileExtnServ.findAll();
//    }
//
//    @ApiOperation("Insert a new Extension and store into Database.")
//    @PostMapping(path = "/extensions/create")
//    public void createExtensions(@ApiParam("Takes JSON of Extension Type as Input") @RequestBody Extensions extension) {
//        fileExtnServ.save(extension);
//    }
//
//    @ApiOperation("Update existing Extension stored into the Database.")
//    @PostMapping(path = "/extensions/update")
//    public void updateExtensions(@ApiParam("Takes JSON of Extension as Input") @RequestBody Extensions extension) {
//        fileExtnServ.save(extension);
//        baseService.afterConst();
//    }
//
//    @ApiOperation("Retrieve the Extension By Id from the Database.")
//    @GetMapping(path = "/extensions/retrive/{id}")
//    public Extensions retriveExtensions(@ApiParam("Takes Extension Id as Input") @PathVariable("id") int extensionId) {
//        return fileExtnServ.getExtensionById(extensionId);
//    }
//
//    @ApiOperation("Delete the List of Extensions from the Database.")
//    @PutMapping(path = "/extensions/delete")
//    public void deleteExtensions(@ApiParam("Takes JSON of Extensions as Input") @RequestBody List<Extensions> extension) {
//        fileExtnServ.deleteMultiple(extension);
//        baseService.afterConst();
//    }
    //Service Services
    @ApiOperation("Retrieve all the Services from the Database.")
    @GetMapping(path = "/services")
    public List<Services> getAllServices() {
        return fileService.findAll();
    }

    @ApiOperation("Insert a new Services and store into Database.")
    @PostMapping(path = "/services/create")
    public void createService(@ApiParam("Takes JSON of Services Type as Input") @RequestBody Services services) {
        fileService.save(services);
    }

    @ApiOperation("Updates the existing Service stored into Database.")
    @PostMapping(path = "/services/update")
    public void updateService(@ApiParam("Takes JSON of Services as Input") @RequestBody Services services) {
        fileService.save(services);
//        baseService.afterConst();
    }

    @ApiOperation("Retrieve the Services By Id from the Database")
    @GetMapping(path = "/services/retrive/{id}")
    public Services retriveServices(@ApiParam("Takes Service Id as Input") @PathVariable("id") int serviceId) {
        return fileService.getServicesById(serviceId);
    }

    @ApiOperation("Delete List of Services from the database.")
    @PutMapping(path = "/services/delete")
    public void deleteServices(@ApiParam("Takes Array of JSON object of servieces as Input") @RequestBody List<Services> service) {
        fileService.deleteAll(service);
//        baseService.afterConst();
    }

    //FileType Services
//    @ApiOperation("Retrieve all the Filetypes from the Database.")
//    @GetMapping(path = "/filetypes")
//    public List<FileTypes> getAllFileTypes() {
//        return fileTypeService.findAll();
//    }
//
//    @ApiOperation("Insert a new Filetype and store into Database.")
//    @PostMapping(path = "/filetypes/create")
//    public void createFileType(@ApiParam("Takes JSON of Filetype as Input") @RequestBody FileTypes filetype) {
//        fileTypeService.save(filetype);
//    }
//    @ApiOperation("Updates the existing Filetype stored into Database.")
//    @PostMapping(path = "/filetypes/update")
//    public void updateFileType(@ApiParam("Takes JSON of Filetype as Input") @RequestBody FileTypes filetype) {
//        fileTypeService.save(filetype);
//        baseService.afterConst();
//    }
//
//    @ApiOperation("Retrieve the Filetype By Id from the Database")
//    @GetMapping(path = "/filetypes/retrive/{id}")
//    public FileTypes retriveFileType(@ApiParam("Takes filetypeId as Input") @PathVariable("id") int filetypeId) {
//        return fileTypeService.getTypeById(filetypeId);
//    }
//
//    @ApiOperation("Deletes the List of Filetype from the Database")
//    @PutMapping(path = "/filetypes/delete")
//    public void deleteFileType(@RequestBody List<FileTypes> fileType) {
//        fileTypeService.deleteAll(fileType);
//        baseService.afterConst();
//    }
    //ServiceFiles services
//    @ApiOperation("Retrieve all the Service To Files Mapping from the Database.")
//    @GetMapping(path = "/servicefiles")
//    public List<ServiceFiles> getAllServiceFile() {
//        return serviceFileservice.findAll();
//    }
//
//    @ApiOperation("Insert a new Service To Files Mapping and store into Database.")
//    @PostMapping(path = "/servicefiles/create")
//    public void createServiceFiles(@ApiParam("Takes JSON of ServiceFiles as Input") @RequestBody ServiceFiles serviceFiles) {
//        serviceFileservice.save(serviceFiles);
//    }
//
//    @ApiOperation("Inserts an Array of Service To Files Mapping and store into Database.")
//    @PostMapping(path = "/servicefiles/createmulti")
//    public void createServiceFilesAll(@ApiParam("Takes Array JSON of ServiceFiles Mapping") @RequestBody List<ServiceFiles> serviceFiles) {
//        serviceFileservice.saveAll(serviceFiles);
//    }
//
//    @ApiOperation("Updates the existing Service To Files Mapping stored into Database.")
//    @PostMapping(path = "/servicefiles/update")
//    public void updateServiceFiles(@ApiParam("Takes JSON of ServiceFiles as Input") @RequestBody ServiceFiles serviceFiles) {
//        serviceFileservice.save(serviceFiles);
//    }
//
//    @ApiOperation("Retrieve the Service_Files By Id from the Database")
//    @GetMapping(path = "/servicefiles/retrive/{id}")
//    public List<ServiceFiles> retriveServiceFiles(@ApiParam("Takes Service Id as Input") @PathVariable("id") int service_id) {
//        return serviceFileservice.getFilesByService(service_id);
//    }
//
//    @ApiOperation("Delete the Service_Files from the Database")
//    @PutMapping(path = "/servicefiles/delete")
//    public void deleteServiceFiles(@ApiParam("Takes Array JSON of ServiceFiles to delete ServiceFile or an Array of ServiceFiles") @RequestBody List<ServiceFiles> serviceFiles) {
//        serviceFileservice.deleteAll(serviceFiles);
//    }
    //ExtensionFileServices
//    @ApiOperation("Retrieve all the Extension To Files Mapping from the Database.")
//    @GetMapping(path = "/extensionfiles")
//    public List<ExtensionFileType> getExtensionFiles() {
//        return extnFileService.findAll();
//    }
//
//    @ApiOperation("Map a new Extensions To File and store into Database.")
//    @PostMapping(path = "/extensionfiles/create")
//    public void createExtensionFiles(@ApiParam("Takes JSON of ExtensionFiles Mapping") @RequestBody ExtensionFileType extensionfileType) {
//        extnFileService.save(extensionfileType);
//    }
//
//    @ApiOperation("Inserts an Array of Extension To Files Mapping and store into Database.")
//    @PostMapping(path = "/extensionfiles/createmulti")
//    public void createExtensionFilesAll(@ApiParam("Takes Array JSON of ExtensionFiles Mapping") @RequestBody List<ExtensionFileType> extensionfileType) {
//        extnFileService.saveAll(extensionfileType);
//    }
//
//    @ApiOperation("Updates the existing Extension To Files Mapping stored into Database.")
//    @PostMapping(path = "/extensionfiles/update")
//    public void updateExtensionFiles(@ApiParam("Takes JSON of ExtensionFiles Mapping") @RequestBody ExtensionFileType extensionfileType) {
//        extnFileService.save(extensionfileType);
//    }
//
//    @ApiOperation("Retrieve the Extension To Files Mapping By FileType Id from the Database")
//    @GetMapping(path = "/extensionfiles/retrive/{id}")
//    public List<ExtensionFileType> getExtensionFilesById(@ApiParam("Takes Extension Id as Input") @PathVariable("id") int filetypeId) {
//        List<ExtensionFileType> temp = extnFileService.getExtenFileTypeById(filetypeId);
//        return temp;
//    }
//
//    @ApiOperation("Delete the Extension_Files from the Database")
//    @PutMapping(path = "/extensionfiles/delete")
//    public void deleteExtensionFileType(@ApiParam("Takes JSON of ExtensionFiles Mapping to delete from database") @RequestBody List<ExtensionFileType> deleteData) {
//        extnFileService.deleteAll(deleteData);
//    }
    // Filetype Extensions Services
    @GetMapping(path = "/filetypeextensions")
    public List<FileTypeExtensions> getFiletypeExtensions() {
        return filetypeExtensionService.findAll();
    }

    @PostMapping(path = "/filetypeextensions")
    public void createFiletypeExtensions(@RequestBody FileTypeExtensions filetypeExtensions) {
        filetypeExtensionService.save(filetypeExtensions);
    }

    @DeleteMapping(path = "/filetypeextensions/id/{id}/filetype/{file_type}")
    public void deleteFiletypeExtensions(@PathVariable("id") String id, @PathVariable("file_type") String file_type) {
        filetypeExtensionService.deletebyid(id, file_type);
    }

    @GetMapping(path = "/filetypeextensions/{extension}")
    public void searchByFiletypeExtensions(@PathVariable("extension") String extension) {
        filetypeExtensionService.getFileExtenions(extension);
    }
//  ServiceFiletype Services

    @GetMapping(path = "/servicefiletypes")
    public List<ServiceFiletypes> getServiceFiletypes() {
        return serviceFiletypesService.findAll();
    }

    @PostMapping(path = "/servicefiletypes/create")
    public void createServiceFiletypes(@RequestBody ServiceFiletypes serviceFileTypes) {
        serviceFiletypesService.save(serviceFileTypes);
    }

    @PutMapping(path = "/servicefiletypes/delete")
    public void deleteServiceFiletypes(@RequestBody ServiceFiletypes serviceFileTypes) {
        serviceFiletypesService.delete(serviceFileTypes);
    }
}
