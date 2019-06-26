package com.sc.fe.analyze.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.service.FileServices;
import com.sc.fe.analyze.service.FiletypeExtensionsService;
import com.sc.fe.analyze.service.ServiceFiletypesService;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.to.ProjectDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    private FileServices fileService;
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
    }

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

    @GetMapping(path = "/servicefiletypes")
    public List<ServiceFiletypes> getServiceFiletypes() {
        return serviceFiletypesService.findAll();
    }

    @GetMapping(path = "/servicefiletypes/id/{serviceid}")
    public List<ServiceFiletypes> getServiceFiletypesById(@PathVariable("serviceid") int serviceId) {
        return serviceFiletypesService.findByKeyServiceId(serviceId);
    }

    @PutMapping(path = "/servicefiletypes/create")
    public void createServiceFiletypes(@RequestBody List<ServiceFiletypes> serviceFileTypes) {
        serviceFiletypesService.save(serviceFileTypes);
    }

    @PutMapping(path = "/servicefiletypes/delete")
    public void deleteServiceFiletypes(@RequestBody List<ServiceFiletypes> serviceFileTypes) {
        serviceFiletypesService.delete(serviceFileTypes);
    }
    
}
