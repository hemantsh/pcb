/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.controller;

import com.sc.fe.analyze.service.FileExtractUploadService;
import java.util.List;
import com.sc.fe.analyze.service.ProjectService;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.Report;
import io.swagger.annotations.Api;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hemant
 */
@RestController
@RequestMapping(path = "/fm")
@CrossOrigin(origins = "*")
@Api(value = "ProjectManagementController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectManagementController {

    @Autowired
    ProjectService projectService;

    @Autowired
    private FileExtractUploadService fileUploadService;

    @PostMapping(path = "/test")
    @ResponseBody
    public ProjectDetails validate(@RequestBody ProjectDetails projectDetails) {

        if (!StringUtils.isEmpty(projectDetails.getrNumber())) {
            Report report = fileUploadService.validateFiles(projectDetails);
        }
        ProjectDetails temp = new ProjectDetails();
        temp.setProjectId(projectDetails.getProjectId());
        temp.setRequestOwner(projectDetails.getRequestOwner());
        temp.setSetId(projectDetails.getSetId());
        temp.setLayers(projectDetails.getLayers());
        temp.setPartNumber(projectDetails.getPartNumber());
        temp.setItar(projectDetails.getItar());
        temp.setNofly(projectDetails.isNofly());
        temp.setNewProject(projectDetails.isNewProject());
        temp.setrNumber(projectDetails.getrNumber());
        temp.setAssemblyTurnTimeQuantity(projectDetails.getAssemblyTurnTimeQuantity());
        temp.setFabricationTurnTimeQuantity(projectDetails.getFabricationTurnTimeQuantity());
        temp.setVersion(projectDetails.getVersion());
        temp.setErrors(projectDetails.getErrors());
        temp.setDifferences(projectDetails.getDifferences());

        return temp;
    }

    @PostMapping(path = "/project")
    @ResponseBody
    public ProjectDetails validateAndSave(@RequestBody ProjectDetails projectDetails) {
        if (projectDetails.isAttachReplace() && !projectDetails.isNewProject()) {
            if (StringUtils.isEmpty(projectDetails.getrNumber())) {
                projectDetails.getErrors().put("V0000", "rNumber is required");
                return validate(projectDetails);
            }
            fileUploadService.compareProject(fileUploadService.returnProjectId(projectDetails));
            Set<String> diff = projectDetails.getDifferences();

            fileUploadService.save(projectDetails);
            projectDetails = fileUploadService.getLatestRecord(projectDetails.getProjectId());
            projectDetails.setAttachReplace(true);
            projectDetails.setDifferences(diff);
            return validate(projectDetails);
        }
        fileUploadService.save(projectDetails);
        if (projectDetails.hasError()) {
            return validate(projectDetails);
        }
        fileUploadService.compareProject(projectDetails);
        return validate(projectDetails);
    }

    @GetMapping("/projects")
    public List<ProjectDetails> getAllProjects(ProjectDetails projectDetails) {
        return projectService.findAll();
    }

    @GetMapping("/project/{projectId}/version/{version}")
    public ProjectDetails getProject(@PathVariable("projectId") String projectId, @PathVariable("version") String verison) {
        return projectService.getProject(projectId, verison);
    }

    @GetMapping("/project/{projectId}")
    public ProjectDetails getProject(@PathVariable("projectId") String projectId) {
        return projectService.getProject(projectId);
    }

    @GetMapping("/project/{projectId}/files")
    public List<FileDetails> getLatestProject(@PathVariable("projectId") String projectId) {
        return projectService.getFileDetails(projectId);
    }

    @GetMapping("/project/{projectId}/differences")
    public Set<String> getDifferences(@PathVariable("projectId") String projectId) {
        return projectService.getDifferences(projectId);
    }

    @GetMapping("/distinctProjectid")
    @ResponseBody
    public Map<String, Set<String>> getDistinctProjectId() {
        return projectService.getProjectVersionMap();
    }
}
