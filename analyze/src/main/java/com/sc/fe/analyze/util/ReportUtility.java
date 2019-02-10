package com.sc.fe.analyze.util;

//import com.sc.fe.analyze.to.ProjectFilesDetails;
import java.util.Date;
import java.util.UUID;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.ProjectDetails;

/**
 *
 * @author Hemant
 */
public class ReportUtility {

    private ReportUtility() {
    }

    public static ProjectDetails convertToObject(Project project) {
    	//TODO check all attributes are set
        ProjectDetails dtl = new ProjectDetails();                
        dtl.setProjectId(project.getProjectId());
        dtl.setVersion(project.getVersion().toString());
        dtl.setCustomerId(project.getCustomerId());
        dtl.setEmailAddress(project.getCustomerEamil());
        dtl.setServiceType(project.getServiceType());
        dtl.setZipFileName(project.getZipFileName());
        dtl.setZipFileSize(project.getZipFileSize());
        dtl.setLayers(project.getLayerCount());
        dtl.setPcbClass(project.getPcbClass());
        dtl.setBoardType(project.getBoardType());
        dtl.setItar(Boolean.valueOf(project.getItar()));
        dtl.setTurnTimeQuantity(project.getTurnTimeQuantity());
        dtl.setDesignSpecification(project.getDesignSpecification());
        dtl.setErrors(project.getErrors());
        dtl.setCreateDate(project.getCreateDate());
        dtl.setModifiedDate(project.getModifiedDate());
        return dtl;
    }

    public static FileDetails convertToObject(ProjectFiles projectFiles) {
    	//TODO check all attributes are set
        FileDetails dtl = new FileDetails();
        dtl.setCopperWeight(projectFiles.getCopperWeight());
        dtl.setType(projectFiles.getType());
        dtl.setName(projectFiles.getName());
        dtl.setVersion(projectFiles.getVersion());
        dtl.setFormat(projectFiles.getFormat());        
        dtl.setModifiedDate(projectFiles.getModifiedDate());
        dtl.setCreateDate(projectFiles.getCreateDate());
        dtl.setSize(projectFiles.getSize());
        dtl.setStep(projectFiles.getStep());
        dtl.setContext(projectFiles.getContext());       
        dtl.setPolarity(projectFiles.getPolarity());
        dtl.setSide(projectFiles.getSide());
        dtl.setLayerOrder(projectFiles.getLayerOrder());
        dtl.setStartName(projectFiles.getStartName());
        dtl.setEndName(projectFiles.getEndName());
        dtl.setCopperWeight(projectFiles.getCopperWeight());
        dtl.setLayerName(projectFiles.getLayerName());
        return dtl;
    }

    public static Project convertToDBObject(ProjectDetails projectDetails) {
    	//TODO check all attributes are set
        Project dbDetail = new Project();
        dbDetail.setCustomerEmail(projectDetails.getEmailAddress());
        dbDetail.setBoardType( projectDetails.getBoardType());
        dbDetail.setCustomerId(projectDetails.getCustomerId());
        dbDetail.setDesignSpecification(projectDetails.getDesignSpecification());
        dbDetail.setErrors(projectDetails.getErrors());
        dbDetail.setPcbClass(projectDetails.getPcbClass());
        dbDetail.setProjectId(projectDetails.getProjectId());
        dbDetail.setVersion(UUID.fromString(projectDetails.getVersion()));

        dbDetail.setTurnTimeQuantity(projectDetails.getTurnTimeQuantity());
        dbDetail.setServiceType(projectDetails.getServiceType());
        dbDetail.setZipFileName(projectDetails.getZipFileName());
        dbDetail.setZipFileSize(projectDetails.getZipFileSize());
        dbDetail.setLayerCount(projectDetails.getLayers());
        dbDetail.setModifiedDate(new Date());
        dbDetail.setCreateDate(new Date());
        dbDetail.setCustomerName("ABC");

        return dbDetail;
    }

    public static ProjectFiles convertToDBObject(FileDetails fileDetails) {
    	//TODO check all attributes are set
        ProjectFiles filesDbDetails = new ProjectFiles();

        filesDbDetails.setVersion(fileDetails.getVersion());
        filesDbDetails.setName(fileDetails.getName());
        filesDbDetails.setSize(fileDetails.getSize());
        filesDbDetails.setType( fileDetails.getType());
        filesDbDetails.setFileDate(new Date());
        filesDbDetails.setFormat(fileDetails.getFormat());
        filesDbDetails.setStep(fileDetails.getStep());
        filesDbDetails.setContext(fileDetails.getContext());
        filesDbDetails.setPolarity(fileDetails.getPolarity());
        filesDbDetails.setSide(fileDetails.getSide());
        filesDbDetails.setLayerOrder(fileDetails.getLayerOrder());
        filesDbDetails.setStartName(fileDetails.getStartName());
        filesDbDetails.setEndName(fileDetails.getEndName());
        filesDbDetails.setCopperWeight(fileDetails.getCopperWeight());
        filesDbDetails.setLayerName(fileDetails.getLayerName());
        filesDbDetails.setAttributes(fileDetails.getAttributes());
        filesDbDetails.setCreateDate(new Date());
        filesDbDetails.setModifiedDate(new Date());
        filesDbDetails.setErrors(fileDetails.getErrors());
        return filesDbDetails;
    }

}
