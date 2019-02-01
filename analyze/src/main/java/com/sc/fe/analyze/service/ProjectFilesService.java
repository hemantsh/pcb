/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.service;

import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.data.repo.ProjectFilesRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hemant
 */
@Service
public class ProjectFilesService {
    @Autowired
    private ProjectFilesRepo projectFilesRepo;
    
    public List<ProjectFiles> findAll()
    {
        return projectFilesRepo.findAll();
    }
    
    public void save(ProjectFiles project_files)
    {
        projectFilesRepo.save(project_files);
    }
    public void delete(ProjectFiles project_files)
    {
        projectFilesRepo.delete(project_files);
    }
    public List<ProjectFiles> getProjectById(String projectId){
        return projectFilesRepo.findByKeyProjectId(projectId);
    }
    
}
