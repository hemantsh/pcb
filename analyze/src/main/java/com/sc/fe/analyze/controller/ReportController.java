/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.controller;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;
import com.sc.fe.analyze.util.ReportUtility;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pc
 */
@RestController
@RequestMapping(path="/report")
@CrossOrigin(origins = "http://localhost:4200")

public class ReportController {
    
    
    @GetMapping("/projects")
    public List<ProjectDetails> getAllProjects(ProjectDetails projectDetails){
        return (List<ProjectDetails>) ReportUtility.convertToDBObject(projectDetails);
    }
    
    @GetMapping("/project/{projectId}/version/{version} ")
    public ProjectDetails getProject(@PathVariable("projectId")String projectId,@PathVariable("version") String verison){
    	
        return null;
    }
}
