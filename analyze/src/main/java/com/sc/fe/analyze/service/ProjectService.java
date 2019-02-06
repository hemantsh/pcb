/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.repo.ProjectRepo;
import com.sc.fe.analyze.to.ProjectDetails;

/**
 *
 * @author pc
 */

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepo projectRepo;
    
    public List<ProjectDetails> findAll(){
    	List<ProjectDetails>  retList = new ArrayList<ProjectDetails> ();
    	List<Project> allRecords = projectRepo.findAll();
    	
    	allRecords.stream().forEach( row -> {
    		//ReportUtility.con
    	});
    	return retList;
    }
    
    public void save(Project project){
        projectRepo.save(project);
    }
    
    public void delete(Project project){
        projectRepo.delete(project);
    }
}
