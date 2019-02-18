/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.DifferenceReport;
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
import com.sc.fe.analyze.data.repo.DifferenceReportRepo;
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
    @Autowired
    private DifferenceReportRepo diffReportRepo;

    /**
     * Displays all the data of Project Table and returns data of ProjectDetails
     * type.
     *
     * @return
     */
    public List<ProjectDetails> findAll() {
        List<Project> allRecords = projectRepo.findAll();
        return convertList(allRecords);
    }

    /**
     * Converts Project data into ProjectDetails type
     *
     * @param allRecords takes List of Project records.
     * @return
     */
    private List<ProjectDetails> convertList(List<Project> allRecords) {
        List<ProjectDetails> retList = new ArrayList<ProjectDetails>();
        allRecords.stream().forEach(row -> {
            retList.add(ReportUtility.convertToObject(row));
        });
        return retList;
    }

    /**
     * Saves Project Data into database.
     *
     * @param project has project records that is stored into the database.
     */
    public void save(Project project) {
        projectRepo.save(project);
    }

    /**
     * Saves DifferenceReport Data into the database.
     *
     * @param diffReport has differnceReport records that is stored into the
     * database
     */
    public void save(DifferenceReport diffReport) {
        diffReportRepo.save(diffReport);
    }

    /**
     * Deletes data from the Project Table into the database.
     *
     * @param project takes value that needs to delete
     */
    public void delete(Project project) {
        projectRepo.delete(project);
    }

    /**
     * Finds the data by CustomerId.
     *
     * @param customerId takes customerId
     * @return
     */
    public List<ProjectDetails> findByCustomerId(String customerId) {
        return convertList(projectRepo.findByCustomerId(customerId));
    }

    /**
     * Finds the data by ProjectId.
     *
     * @param projectId takes projectId.
     * @return
     */
    public List<ProjectDetails> findByKeyProjectId(String projectId) {
        return convertList(projectRepo.findByKeyProjectId(projectId));
    }

    /**
     * Finds the data by CustomerEmail
     *
     * @param customerEmail takes customerEmail
     * @return
     */
    public List<ProjectDetails> findByCustomerEmail(String customerEmail) {
        return convertList(projectRepo.findByCustomerEmail(customerEmail));
    }

    /**
     * Finds the data by ZipFileName
     *
     * @param zipFileName takes zipFileName
     * @return
     */
    public List<ProjectDetails> findByZipFileName(String zipFileName) {
        return convertList(projectRepo.findByZipFileName(zipFileName));
    }

    /**
     * Displays data of one unique projectId mapped with multiple versions.
     *
     * @return
     */
    public Map<String, Set<String>> getProjectVersionMap() {
        //Key = projectID
        //Value - set of versions for the projectId
        List<Project> project = projectRepo.findAll();

        Map<String, Set<String>> projVersionMap = new HashMap<String, Set<String>>();
        if (project != null) {
            project.stream().forEach(proj -> {

                String key = proj.getProjectId();
                String version = proj.getVersion().toString();

                if (projVersionMap.containsKey(key)) {

                    projVersionMap.get(key).add(version);

                } else {
                    Set<String> vSet = new HashSet<String>();
                    vSet.add(version);
                    projVersionMap.put(key, vSet);
                }
            });
        }
        return projVersionMap;
    }

    /**
     * Displays ProjectDetails which has projectId and version.
     *
     * @param projectId takes ProjectId
     * @param verison takes version
     * @return
     */
    public ProjectDetails getProject(String projectId, String verison) {
        ProjectPK projectPK = new ProjectPK();
        projectPK.setProjectId(projectId);
        projectPK.setVersion(UUID.fromString(verison));
        Optional<Project> findByID = projectRepo.findById(projectPK);

        ProjectDetails obj = ReportUtility.convertToObject(findByID.get());
        List<ProjectFiles> fileList = projectFilesRepo.findByKeyProjectIdAndKeyVersion(projectId, UUID.fromString(verison));
        List<FileDetails> fbList = new ArrayList<FileDetails>();
        fileList.stream().forEach(row -> {
            fbList.add(ReportUtility.convertToObject(row));
        });
        obj.setFileDetails(fbList);
        return obj;
    }
}
