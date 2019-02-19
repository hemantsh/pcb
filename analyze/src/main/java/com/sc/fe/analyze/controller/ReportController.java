/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sc.fe.analyze.service.ProjectService;
import com.sc.fe.analyze.to.ProjectDetails;

/**
 *
 * @author pc
 */
@RestController
@RequestMapping(path="/report")
@CrossOrigin(origins = "*")

public class ReportController {
    @Autowired
    ProjectService projectService;
    
    @GetMapping("/projects")
    public List<ProjectDetails> getAllProjects(ProjectDetails projectDetails){       
       return projectService.findAll();       
    }
    
    @GetMapping("/project/{projectId}/version/{version}")
    public ProjectDetails getProject(@PathVariable("projectId")String projectId,@PathVariable("version") String verison){    	
        return projectService.getProject(projectId, verison);
    }
    
    @GetMapping("/distinctProjectid")
    @ResponseBody
    public Map<String, Set<String>> getDistinctProjectId(){
        return projectService.getProjectVersionMap();
    }
    
    @GetMapping("/project/{projectId}/differences")
    public Set<String> getDifferences(@PathVariable("projectId") String projectId)
    {
        return projectService.getDifferences(projectId);        
    }
}
