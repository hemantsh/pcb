package com.sc.fe.analyze.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.datastax.driver.core.utils.UUIDs;
import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.to.FileDetails;
import com.sc.fe.analyze.to.FileTypeExtensions;
import com.sc.fe.analyze.to.ProjectDetails;
import com.sc.fe.analyze.to.TurnTimeQuantity;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * Utility Class converts the entity Classes attributes to to Classes attributes
 * and vice-versa.
 *
 * @author Hemant
 */
public class ReportUtility {

    private ReportUtility() {
    }

    /**
     * It sets the each ProjectDetails Class attributes from the Project Class
     * attributes
     *
     * @param project the details of the Project Class
     * @return the ProjectDetails Object
     */
    public static ProjectDetails convertToObject(Project project) {
        ProjectDetails dtl = new ProjectDetails();
        dtl.setSetId(project.getSetId());
        dtl.setProjectId(project.getProjectId());
        dtl.setVersion(project.getVersion().toString());
        dtl.setAssemblySpec(project.getAssemblySpec());
        dtl.setCompany(project.getCompany());
        dtl.setPartRev(project.getPartRev());
        dtl.setCustomerId(project.getCustomerId());
        dtl.setCustomerName(project.getCustomerName());
        dtl.setEmailAddress(project.getCustomerEmail());
        dtl.setServiceType(project.getServiceType());
        dtl.setZipFileName(project.getZipfileName());
        dtl.setZipFileSize(project.getZipfileSize());
        dtl.setLayers(project.getLayerCount());
        dtl.setPcbClass(project.getPcbClass());
        dtl.setBoardType(project.getBoardType());
        dtl.setPartNumber(project.getPartNumber());
        dtl.setrNumber(project.getrNumber());
        dtl.setItar(project.getItar());
        if (project.getFabricationTurntimeQuantity() != null) {
            String check = project.getFabricationTurntimeQuantity();
            dtl.setFabricationTurnTimeQuantity(convertToList(check));
        }
        if (project.getAssemblyTurntimeQuantity() != null) {
            String check = project.getAssemblyTurntimeQuantity();
            dtl.setAssemblyTurnTimeQuantity(convertToList(check));
        }

        dtl.setDesignSpecification(project.getDesignSpecification());
        dtl.setErrors(project.getErrors());
        dtl.setCreateDate(project.getCreateDate());
        dtl.setModifiedDate(project.getModifiedDate());
        dtl.setNofly(project.getNofly());
        return dtl;
    }

    /**
     * It sets the each FileDetails Class attributes from the ProjectFiles Class
     * attributes
     *
     * @param projectFiles the details of the ProjectFiles Class
     * @return the FileDetails Object
     */
    public static FileDetails convertToObject(ProjectFiles projectFiles) {
        FileDetails dtl = new FileDetails();
        dtl.setFileDate(projectFiles.getFileDate());
        dtl.setName(projectFiles.getName());
        dtl.setVersion(projectFiles.getVersion());
        dtl.setFormat(projectFiles.getFormat());
        dtl.setCopperWeight(projectFiles.getCopperWeight());
        dtl.setType(projectFiles.getType());
        dtl.setCreateDate(projectFiles.getCreateDate());
        dtl.setFileSize(projectFiles.getSize());
        dtl.setStatus(projectFiles.getStatus());
        dtl.setStep(projectFiles.getStep());
        dtl.setContext(projectFiles.getContext());
        dtl.setPolarity(projectFiles.getPolarity());
        dtl.setSide(projectFiles.getSide());
        dtl.setLayerOrder(projectFiles.getLayerOrder());
        dtl.setStartName(projectFiles.getStartName());
        dtl.setEndName(projectFiles.getEndName());
        dtl.setLayerName(projectFiles.getLayerName());
        dtl.setAttributes(projectFiles.getAttributes());
        dtl.setErrors(projectFiles.getErrors());
        dtl.setSelected(projectFiles.isSelected());
        dtl.setPath(projectFiles.getPath());
        dtl.setFullName(projectFiles.getFullName());

        return dtl;
    }

    public static FileTypeExtensions convertToObject(FiletypeExtensions filetypeExtn) {

        FileTypeExtensions fe = new FileTypeExtensions();

        fe.setId(filetypeExtn.getKey().getId().toString());
        fe.setFile_type(filetypeExtn.getKey().getFiletype());
        fe.setExtensions(extnToString(filetypeExtn.getExtensions()));
        return fe;
    }

    public static FiletypeExtensions convertToDBObject(FileTypeExtensions fe) {
        FiletypeExtensions filetypeExtn = new FiletypeExtensions();

//        filetypeExtn.getKey().setId(UUID.fromString(fe.getId()));
        if (fe.getId() == null) {
            filetypeExtn.getKey().setId(UUIDs.timeBased());
            filetypeExtn.getKey().setFiletype(fe.getFile_type());
            filetypeExtn.setExtensions(convertToSet(fe.getExtensions()));
        }
        if (fe.getId() != null) {
            filetypeExtn.getKey().setId(UUID.fromString(fe.getId()));
            filetypeExtn.getKey().setFiletype(fe.getFile_type());
            filetypeExtn.setExtensions(convertToSet(fe.getExtensions()));
        }

        return filetypeExtn;
    }

    /**
     * It sets the each Project Class attributes from the ProjectDetails Class
     * attributes
     *
     * @param projectDetails the details of the ProjectDetails Class
     * @return the Project Object
     */
    public static Project convertToDBObject(ProjectDetails projectDetails) {
        //TODO check all attributes are set
        Project dbDetail = new Project();
        dbDetail.setPartNumber(projectDetails.getPartNumber());
        dbDetail.setrNumber(projectDetails.getrNumber());
        dbDetail.setSetId(projectDetails.getSetId());
        dbDetail.setCustomerName(projectDetails.getCustomerName());
        dbDetail.setCustomerEmail(projectDetails.getEmailAddress());
        dbDetail.setAssemblySpec(projectDetails.getAssemblySpec());
        dbDetail.setCompany(projectDetails.getCompany());
        dbDetail.setPartRev(projectDetails.getPartRev());
        dbDetail.setBoardType(projectDetails.getBoardType());
        dbDetail.setCustomerId(projectDetails.getCustomerId());
        dbDetail.setDesignSpecification(projectDetails.getDesignSpecification());
        dbDetail.setErrors(projectDetails.getErrors());
        dbDetail.setPcbClass(projectDetails.getPcbClass());
        dbDetail.setProjectId(projectDetails.getProjectId());
        if( StringUtils.isNotEmpty(projectDetails.getVersion()) ) {
        	dbDetail.setVersion(UUID.fromString(projectDetails.getVersion()));
        }
        if (projectDetails.getAssemblyTurnTimeQuantity() != null) {
            String joined = projectDetails.getAssemblyTurnTimeQuantity().stream().map(Object::toString).collect(Collectors.joining(","));
            dbDetail.setAssemblyTurntimeQuantity(joined);
        }
        if (projectDetails.getFabricationTurnTimeQuantity() != null) {
            String joined = projectDetails.getFabricationTurnTimeQuantity().stream().map(Object::toString).collect(Collectors.joining(","));
            dbDetail.setFabricationTurntimeQuantity(joined);
        }
        dbDetail.setServiceType(projectDetails.getServiceType());
        dbDetail.setZipfileName(projectDetails.getZipFileName());
        dbDetail.setZipfileSize(projectDetails.getZipFileSize());
        dbDetail.setLayerCount(projectDetails.getLayers());
        dbDetail.setModifiedDate(new Date());
        if (projectDetails.getCreateDate() == null) {
            dbDetail.setCreateDate(new Date());
        } else {
            dbDetail.setCreateDate(projectDetails.getCreateDate());
        }

        dbDetail.setItar(projectDetails.getItar());
        dbDetail.setPcbClass(projectDetails.getPcbClass());
        dbDetail.setNofly(projectDetails.isNofly());

        return dbDetail;
    }

    /**
     * It sets the each ProjectFiles Class attributes from the FileDetails Class
     * attributes
     *
     * @param fileDetails the details of the FileDetails Class
     * @return the ProjectFiles Object
     */
    public static ProjectFiles convertToDBObject(FileDetails fileDetails) {
        //TODO check all attributes are set
        ProjectFiles filesDbDetails = new ProjectFiles();
        filesDbDetails.setVersion(fileDetails.getVersion());
        filesDbDetails.setName(fileDetails.getName());
        filesDbDetails.setSize(fileDetails.getFileSize());
        filesDbDetails.setType(fileDetails.getType());
        if (fileDetails.getModifiedDate() == null) {
            filesDbDetails.setFileDate(new Date());
        } else {
            filesDbDetails.setFileDate(fileDetails.getModifiedDate());
        }
        filesDbDetails.setFormat(fileDetails.getFormat());
        filesDbDetails.setStep(fileDetails.getStep());
        if (fileDetails.getStatus() == null) {
            filesDbDetails.setStatus("active");
        } else {
            filesDbDetails.setStatus(fileDetails.getStatus());
        }
        filesDbDetails.setContext(fileDetails.getContext());
        filesDbDetails.setPolarity(fileDetails.getPolarity());
        filesDbDetails.setSide(fileDetails.getSide());
        filesDbDetails.setLayerOrder(fileDetails.getLayerOrder());
        filesDbDetails.setStartName(fileDetails.getStartName());
        filesDbDetails.setEndName(fileDetails.getEndName());
        filesDbDetails.setCopperWeight(fileDetails.getCopperWeight());
        filesDbDetails.setLayerName(fileDetails.getLayerName());
        filesDbDetails.setAttributes(fileDetails.getAttributes());
        if (fileDetails.getCreateDate() == null) {
            filesDbDetails.setCreateDate(new Date());
        } else {
            filesDbDetails.setCreateDate(fileDetails.getCreateDate());
        }
        filesDbDetails.setModifiedDate(new Date());
        filesDbDetails.setErrors(fileDetails.getErrors());
        filesDbDetails.setSelected(fileDetails.isSelected());
        if( ! StringUtils.isEmpty(fileDetails.getPath()) ) {
        	filesDbDetails.setPath(fileDetails.getPath());
        }
        if( ! StringUtils.isEmpty(fileDetails.getFullName()) ) {
        	filesDbDetails.setFullName(fileDetails.getFullName());
        }
        return filesDbDetails;
    }

    //This method converts the String type to List of TurnTimeQuantity
    private static List<TurnTimeQuantity> convertToList(String qtyDetails) {
        List<TurnTimeQuantity> turnTime = new ArrayList<TurnTimeQuantity>();
        List<String> myList = new ArrayList<String>(Arrays.asList(qtyDetails.split(",")));
        for (int i = 0; i < myList.size(); i++) {
            String[] result = myList.get(i).split(":");
            turnTime.add(new TurnTimeQuantity(result[0], Integer.parseInt(result[1])));
        }
        return turnTime;
    }

    private static Set<String> convertToSet(String extensions) {
        Set<String> mySet = new HashSet<String>(Arrays.asList(extensions.split(",")));
        return mySet;
    }

    private static String extnToString(Set<String> extensions) {
        String stringExtensions = "";
        if (extensions == null) {
            return stringExtensions;
        }
        for (String extn : extensions) {
            if (stringExtensions.isEmpty()) {
                stringExtensions = extn;
            } else {
                stringExtensions += "," + extn;
            }
        }
        return stringExtensions;
    }

}
