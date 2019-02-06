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
import com.sc.fe.analyze.util.ReportUtility;

/**
 *
 * @author Hemant
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

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

    public void save(Project project) {
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

    public List<Project> findByCustomerEmail(String customerEmail) {
        return projectRepo.findByCustomerEmail(customerEmail);
    }

    public List<Project> findByZipFileName(String zipFileName) {
        return projectRepo.findByZipFileName(zipFileName);
    }
}
