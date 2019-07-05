package com.sc.fe.analyze.controller;

import java.util.ArrayList;
import java.util.List;
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

import com.sc.fe.analyze.service.FileExtractUploadService;
import com.sc.fe.analyze.service.ProjectService;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;

import io.swagger.annotations.Api;

/**
 *
 * @author Hemant
 */
@RestController
@RequestMapping(path = "/fm")
@CrossOrigin(origins = "*")
@Api(value = "ProjectManagementController", produces = MediaType.APPLICATION_JSON_VALUE  )
public class ProjectManagementController {

    @Autowired
    ProjectService projectService;    
    @Autowired
    private FileExtractUploadService fileUploadService;
    
    @GetMapping(path="/project/rnumber/{rnumber}")
    public ProjectDetails getProjectWithRNumber( @PathVariable("rnumber") String rNumber) {
    	    	
        List<ProjectDetails> projDtl = projectService.findByrNumber(rNumber);
        ProjectDetails latestRecord = fileUploadService.getLatestRecord(projDtl);
    	//Only project information is required. Nullify rest info
    	latestRecord.setErrors(null);
    	return latestRecord;
    }

    
    public ProjectDetails validate(@RequestBody ProjectDetails projectDetails) {
       
        if (!StringUtils.isEmpty(projectDetails.getrNumber())) {
            fileUploadService.validateFiles(projectDetails);
        }
        ProjectDetails temp = new ProjectDetails();
        temp.setProjectId(projectDetails.getProjectId());
        temp.setSetId(projectDetails.getSetId());
        temp.setLayers(projectDetails.getLayers());
        temp.setPartNumber(projectDetails.getPartNumber());
        temp.setItar(projectDetails.getItar());
        temp.setNofly(projectDetails.isNofly());
        temp.setNewProject(projectDetails.isNewProject());
        temp.setrNumber(projectDetails.getrNumber());
        temp.setAssemblyTurnTimeQuantity(projectDetails.getAssemblyTurnTimeQuantity());
        temp.setFabricationTurnTimeQuantity(projectDetails.getFabricationTurnTimeQuantity());
        
        if( StringUtils.isEmpty(projectDetails.getSetId()) ){
            temp.setErrors(projectDetails.getErrors());
            temp.setDifferences(projectDetails.getDifferences());   
            temp.setFileChanges(projectDetails.getFileChanges());
        }else{
            temp.setVersion(projectDetails.getVersion());
        }
        return temp;
    }
    
    private boolean hasRequiredFields( ProjectDetails projectDetails ) {
    	boolean retVal = true;
    	if (StringUtils.isEmpty(projectDetails.getrNumber())) {
    		retVal = false;
    	}
    	return retVal;
    }

    @PostMapping(path = "/project")
    @ResponseBody
    public ProjectDetails validateAndSave(@RequestBody ProjectDetails projectDetails) {   
    	if( ! hasRequiredFields(projectDetails)) {
    		projectDetails.getErrors().put("V0000", "rNumber is required");
    		projectDetails.setFileDetails(new ArrayList<FileDetails>(0));
    		return projectDetails;
    	}
    	ProjectDetails retDetails= null;
    	
    	ProjectDetails temp = fileUploadService.getProjectByRNumber(projectDetails.getrNumber());
    	
    	if( temp != null) {
    		projectDetails.setProjectId(temp.getProjectId());
    		projectDetails.setVersion( temp.getVersion());
    	}
    	
    	boolean validate = StringUtils.isEmpty(projectDetails.getSetId());
    	if(validate) {
    		fileUploadService.processGerber(projectDetails.getFileDetails());
    		//
    		String tVer = new String(projectDetails.getVersion() == null ? "" : projectDetails.getVersion() );
    		projectDetails.setVersion("");
    		fileUploadService.compareProject(projectDetails);    
    		projectDetails.setVersion(tVer);
    		retDetails = validate(projectDetails);
    	}else {
    		fileUploadService.save(projectDetails);
    		retDetails = projectDetails;
    		retDetails.setFileDetails(new ArrayList<FileDetails>(0));
    	}
    	
        return retDetails;
    }

   

    @GetMapping("/projects")
    public List<ProjectDetails> getAllProjects() {
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
    public List<FileDetails> getLatestProjectFiles(@PathVariable("projectId") String projectId) {
        return projectService.getFileDetails(projectId);
    }

    @GetMapping("/project/{projectId}/differences")
    public String getDifferences(@PathVariable("projectId") String projectId) {
        return projectService.getDifferencesJson(projectId);
    }
    
}
