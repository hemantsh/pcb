package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.DifferenceReportJson;
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
import com.sc.fe.analyze.data.repo.DifferenceReportJsonRepo;
import com.sc.fe.analyze.data.repo.ProjectFilesRepo;
import com.sc.fe.analyze.data.repo.ProjectRepo;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.util.ReportUtility;
import org.springframework.util.StringUtils;

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
    private DifferenceReportJsonRepo diffReportJsonRepo;

    /**
     * Displays all the data of Project Table and returns data of ProjectDetails
     * type.
     *
     * @return the list of projectDetails
     */
    public List<ProjectDetails> findAll() {
        List<Project> allRecords = projectRepo.findAll();
        return convertList(allRecords);
    }

    /**
     * Converts Project data into ProjectDetails type
     *
     * @param allRecords takes List of Project records.
     * @return the list of projectDetails
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
     * Saves DifferenceReportJson Data into the database.
     *
     * @param diffReport has differnceReportJson records that is stored into the
     * database
     */
    public void save(DifferenceReportJson diffReport) {
        diffReportJsonRepo.save(diffReport);

    }

    /**
     * Deletes data from the Project Table into the database.
     *
     * @param project takes the value of project that needs to delete
     */
    public void delete(Project project) {
        projectRepo.delete(project);
    }

    /**
     * Finds the data by CustomerId.
     *
     * @param customerId takes customerId
     * @return the list of projectDetails
     */
    public List<ProjectDetails> findByCustomerId(String customerId) {
        return convertList(projectRepo.findByCustomerId(customerId));
    }

    public List<ProjectDetails> findByrNumber(String rNumber) {
        return convertList(projectRepo.findByRNumber(rNumber));
    }

    /**
     * Finds the data by ProjectId.
     *
     * @param projectId takes the projectId.
     * @return the list of projectDetails
     */
    public List<ProjectDetails> findByKeyProjectId(String projectId) {
        return convertList(projectRepo.findByKeyProjectId(projectId));
    }

    /**
     * Finds the data by CustomerEmail
     *
     * @param customerEmail takes customerEmail
     * @return the list of projectDetails
     */
    public List<ProjectDetails> findByCustomerEmail(String customerEmail) {
        return convertList(projectRepo.findByCustomerEmail(customerEmail));
    }

    /**
     * Finds the data by ZipFileName
     *
     * @param zipFileName takes zipFileName
     * @return the list of projectDetails
     */
    public List<ProjectDetails> findByZipFileName(String zipFileName) {
        return convertList(projectRepo.findByZipFileName(zipFileName));
    }

    /**
     * Displays data of one unique projectId mapped with multiple versions.
     *
     * @return the value in (key-value) pair
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
     * Displays the ProjectDetails which matches with the projectId and version.
     *
     * @param projectId takes ProjectId
     * @param verison takes version
     * @return the projectDetails
     */
    public ProjectDetails getProject(String projectId, String verison) {
        ProjectPK projectPK = new ProjectPK();
        if(StringUtils.isEmpty(projectId) || StringUtils.isEmpty(verison)){
            return new ProjectDetails();
        }
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

    /**
     * Displays latest ProjectDetails which matches the projectId in the
     * argument.
     *
     * @param projectId takes ProjectId
     * @return the latest projectDetails of projectId
     */
    public ProjectDetails getProject(String projectId) {
        ProjectDetails project = null;
        List<ProjectDetails> projDtl = convertList(projectRepo.findByKeyProjectId(projectId));
        project = projDtl.stream()
                .max((a1, a2) -> a1.getCreateDate().compareTo(a2.getCreateDate())).orElse(null);
        List<ProjectFiles> fileList = projectFilesRepo.findByKeyProjectIdAndKeyVersion(project.getProjectId(), UUID.fromString(project.getVersion()));
        List<FileDetails> fbList = new ArrayList<FileDetails>();
        fileList.stream().forEach(row -> {
            fbList.add(ReportUtility.convertToObject(row));
        });
        project.setFileDetails(fbList);
        return project;
    }

    /**
     * This method takes the projectId and get all the differences
     *
     * @param projectId Takes the projectId
     * @return the set of string
     */
    public String getDifferencesJson(String projectId) {
        Optional<DifferenceReportJson> diff = diffReportJsonRepo.findById(projectId);
        if (diff.isPresent()) {
            return diff.get().getDifferences();
        }

        return null;
    }

    /**
     * This method display the latest FileDetails which matches the projectId
     *
     * @param projectId Takes project Id
     * @return the latest list of FileDetails
     */
    public List<FileDetails> getFileDetails(String projectId) {
        List<ProjectDetails> projDtl = convertList(projectRepo.findByKeyProjectId(projectId));
        ProjectDetails project = projDtl.stream()
                .max((a1, a2) -> a1.getCreateDate().compareTo(a2.getCreateDate())).orElse(null);
        List<ProjectFiles> fileList = projectFilesRepo.findByKeyProjectIdAndKeyVersion(project.getProjectId(), UUID.fromString(project.getVersion()));
        List<FileDetails> fbList = new ArrayList<FileDetails>();
        fileList.stream().forEach(row -> {
            fbList.add(ReportUtility.convertToObject(row));
        });
        project.setFileDetails(fbList);
        return fbList;
    }
}
