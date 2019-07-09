package com.sc.fe.analyze.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.data.repo.ProjectFilesRepo;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.util.ReportUtility;

/**
 *
 * @author Hemant
 */
@Service
public class ProjectFilesService {

    @Autowired
    private ProjectFilesRepo projectFilesRepo;

    /**
     * Displays all the records in the ProjectFiles tables and returns data of
     * fileDetails Type.
     *
     * @return the list of FileDetails
     */
    public List<FileDetails> findAll() {
        List<ProjectFiles> allRecords = projectFilesRepo.findAll();
        return convertList(allRecords);
    }

    /**
     * Converts the ProjectFiles data into FileDetails
     *
     * @param allRecords takes List of ProjectFiles records
     * @return
     */
    private List<FileDetails> convertList(List<ProjectFiles> allRecords) {
    	
        List<FileDetails> retList = new ArrayList<FileDetails>();
        if(allRecords != null) {
        	allRecords.stream().forEach(row -> {
        
	            retList.add(ReportUtility.convertToObject(row));
	        });
        }
        return retList;
    }

    /**
     * Saves ProjectFiles data in database.
     *
     * @param project_files has projectFiles that needs to saved into database.
     */
    public void save(ProjectFiles project_files) {
        projectFilesRepo.save(project_files);
    }

    /**
     * Deletes the Records from the Database.
     *
     * @param project_files has values that needs to delete from the database.
     */
    public void delete(ProjectFiles project_files) {
    	projectFilesRepo.delete(project_files);
    }

    
    /**
     * Get the project files for specific version
     * @param projectId
     * @param version
     * @return
     */
    public List<FileDetails> getProjectFiles(String projectId, String version) {
        return convertList(projectFilesRepo.findByKeyProjectIdAndKeyVersion (projectId, UUID.fromString(version)) );
    }

}
