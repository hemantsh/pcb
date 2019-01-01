/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.controller;

import com.sc.fe.analyze.data.entity.Extensions;
import com.sc.fe.analyze.data.entity.FileTypes;
import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.entity.Services;
import com.sc.fe.analyze.service.FileExtensionService;
import com.sc.fe.analyze.service.FileServices;
import com.sc.fe.analyze.service.FileTypeService;
import com.sc.fe.analyze.service.ReportServices;
import com.sc.fe.analyze.service.ServiceFilesServices;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pc
 */
@RestController
@RequestMapping(path="/admin")
@CrossOrigin(origins = "http://localhost:4200")
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
    
    //Extension Services
    @GetMapping(path="/extensions")
    public List<Extensions> getAllExtensions(){
        return  fileExtnServ.findAll();
    }
    @PostMapping(path="/extensions/create")
    public void createExtensions(@RequestBody Extensions extn){
         fileExtnServ.save(extn);
    }
    @GetMapping(path="/extensions/retrive/{id}")
    public Extensions retriveExtensions(@PathVariable("id") int id){
         return fileExtnServ.getExtensionById(id);
    }
    
    
    
    //Service Services
    @GetMapping(path="/services")
    public List<Services> getAllServices(){
        return fileService.findAll();
    }
    @PostMapping(path="/services/create")
    public void createService(@RequestBody Services services){
        fileService.save(services);
    }
    
    @GetMapping(path="/services/retrive/{id}")
    public Services retriveServices(@PathVariable("id") int id ){
    return fileService.getServicesById(id);
    }
    
    
    //FileType Services
    @GetMapping(path="/filetypes")
    public List<FileTypes> getAllFileTypes(){
        return fileTypeService.findAll();
    }
    @PostMapping(path="/filetypes/create")
    public void createFileType(@RequestBody FileTypes ft){
    fileTypeService.save(ft);
    }
    @GetMapping(path="/filetypes/retrive/{id}")
    public FileTypes retriveFileType(@PathVariable("id")int id){
        return fileTypeService.getTypeById(id);
    }
    
    //ServiceFiles services
    @GetMapping(path="/servicefiles")
    public List<ServiceFiles> getAllServiceFile()
    {
        return serviceFileservice.findAll();
    }
    
    @PostMapping(path="/servicefiles/create")
    public void createServiceFiles(@RequestBody ServiceFiles sf){
    serviceFileservice.save(sf);
    }
    
    @GetMapping(path="/servicefiles/retrive/{id}")
    public ServiceFiles retriveServiceFiles(@PathVariable("id")int id){
        return serviceFileservice.getFilesByService(id);
    }
    
    
    //Report Services
    
    @GetMapping(path="/report")
    public List<Report> getAllReport(){
        return reportServices.findAll();
    }
    @PostMapping(path="/report/create")
    public void createReport(@RequestBody Report report){
       reportServices.save(report);
    }
}
