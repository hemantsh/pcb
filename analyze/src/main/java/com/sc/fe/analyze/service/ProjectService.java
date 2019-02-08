/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.data.entity.ProjectPK;
import com.sc.fe.analyze.data.repo.ProjectFilesRepo;
import com.sc.fe.analyze.data.repo.ProjectRepo;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.util.ReportUtility;

/**
 *
 * @author Hemant
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private ProjectFilesRepo projectFilesRepo;

    public List<ProjectDetails> findAll() {        
        List<Project> allRecords = projectRepo.findAll();
        return convertList(allRecords);        
    }

    private List<ProjectDetails> convertList(List<Project> allRecords) {
        List<ProjectDetails> retList = new ArrayList<ProjectDetails>();
        allRecords.stream().forEach(row -> {
            retList.add(ReportUtility.convertToObject(row));
        });
        return retList;
    }

//    public void save(ProjectDetails projectDetails) {
//        Project allRecords =  ReportUtility.convertToDBObject(projectDetails);
//        projectRepo.save(allRecords);
//    }
    public void save(Project project){
        projectRepo.save(project);
    }

    public void delete(Project project) {
        projectRepo.delete(project);
    }

    public List<ProjectDetails> findByCustomerId(String customerId) {       
        return convertList(projectRepo.findByCustomerId(customerId));
    }

    public List<ProjectDetails> findByKeyProjectId(String projectId) {
        return convertList(projectRepo.findByKeyProjectId(projectId));
    }

    public List<ProjectDetails> findByCustomerEmail(String customerEmail) {
        return convertList(projectRepo.findByCustomerEmail(customerEmail));
    }

    public List<ProjectDetails> findByZipFileName(String zipFileName) {
        return convertList(projectRepo.findByZipFileName(zipFileName));
    }
    
    public Map<String, Set<String>> getProjectVersionMap() {
    	//Key = projectID
    	//Value - set of versions for the projectId
         List<Project> project=projectRepo.findAll();
         
         Map<String, Set<String>> projVersionMap =new HashMap<String, Set<String>>();
         if(project!=null){
             project.stream().forEach( proj -> {
            	 
            	 String key = proj.getProjectId();
            	 String version = proj.getVersion().toString();
            	 
            	 if(projVersionMap.containsKey(key) ) {
            		 
            		 projVersionMap.get(key).add(version);
            		 
            	 }else {
            		 Set<String> vSet = new HashSet<String>();
            		 vSet.add(version);
            		 projVersionMap.put(key,vSet);
            	 }
             });
         }
         return projVersionMap;
    }
    
    public ProjectDetails getProject(String projectId,String verison){
        ProjectPK projectPK=new ProjectPK();
        projectPK.setProjectId(projectId);
        projectPK.setVersion(UUID.fromString(verison));
        Optional<Project> findByID=projectRepo.findById(projectPK);
        
        ProjectDetails obj= ReportUtility.convertToObject(findByID.get());
        List<ProjectFiles> fileList=projectFilesRepo.findByKeyProjectIdAndKeyVersion(projectId,UUID.fromString(verison));
        List<FileDetails> fbList=new ArrayList<FileDetails>();
        fileList.stream().forEach(row -> {
            fbList.add(ReportUtility.convertToObject(row));
        });
        obj.setFileDetails(fbList);
        return obj;
    }
    
    public void setProject(String projectId,String version){
        /**
         * TODO
         */
    }
}
