package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;

import java.util.HashMap;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "ProjectDetails", description = "Object model for ProjectDetails")
@JsonInclude(Include.NON_EMPTY)
public class ProjectDetails implements Serializable {

    private static final long serialVersionUID = 7229557964688858057L;

    private String projectId;
    private String version;
    private String partNumber;
    private String rNumber;
    private boolean attachReplace;
    private boolean newProject;
    private String setId;
    private String company;
    private String partRev;
    private String assemblySpec;
    private String serviceType;
    private String zipFileName;
    private String zipFileSize;
    private int layers;
    private Date modifiedDate;
    private Date createDate;
    private String pcbClass;
    private String boardType;
    private boolean itar;
    private boolean nofly;
    private List<TurnTimeQuantity> assemblyTurnTimeQuantity;
    private List<TurnTimeQuantity> fabricationTurnTimeQuantity;
    private String designSpecification;
    private String customerId;
    private String customerName;
    private String emailAddress;
    private Map<String, String> errors; //Errors in (key:value) form
    private Differences differences;
    List<FileChange> fileChanges;

    /**
     * Details about the files we found while processing
     */
    private List<FileDetails> fileDetails = new ArrayList<FileDetails>();

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPartRev() {
        return partRev;
    }

    public void setPartRev(String partRev) {
        this.partRev = partRev;
    }

    public String getAssemblySpec() {
        return assemblySpec;
    }

    public void setAssemblySpec(String assemblySpec) {
        this.assemblySpec = assemblySpec;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getrNumber() {
        return rNumber;
    }

    public void setrNumber(String rNumber) {
        this.rNumber = rNumber;
    }

    public List<TurnTimeQuantity> getAssemblyTurnTimeQuantity() {
        return assemblyTurnTimeQuantity;
    }

    public void setAssemblyTurnTimeQuantity(List<TurnTimeQuantity> assemblyTurnTimeQuantity) {
        this.assemblyTurnTimeQuantity = assemblyTurnTimeQuantity;
    }

    public List<TurnTimeQuantity> getFabricationTurnTimeQuantity() {
        return fabricationTurnTimeQuantity;
    }

    public void setFabricationTurnTimeQuantity(List<TurnTimeQuantity> fabricationTurnTimeQuantity) {
        this.fabricationTurnTimeQuantity = fabricationTurnTimeQuantity;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<FileDetails> getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
    }

    public void addFileDetail(FileDetails fileDetail) {
        if (fileDetails == null) {
            fileDetails = new ArrayList<FileDetails>();
        }
        fileDetails.add(fileDetail);
    }

    public void replaceFileDetail(FileDetails fileDetail) {
        if (fileDetail == null || StringUtils.isEmpty(fileDetail.getName())) {
            return;
        }
        FileDetails fd = getFileDetails(fileDetail.getName());
        if (fd != null) {
            this.fileDetails.remove(fileDetail);
        }
        this.fileDetails.add(fileDetail);
    }

    @JsonIgnore
    public Set<String> getAllFileNames() {
        if (fileDetails == null) {
            return null;
        }
        return fileDetails.stream()
                .map(FileDetails::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    @JsonIgnore
    public Set<String> getAllSelectedFileNames() {
        if (fileDetails == null) {
            return null;
        }
        return fileDetails.stream()
                .filter(fd -> fd.isSelected())
                .map(FileDetails::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public FileDetails getFileDetails(String fileName) {
        FileDetails retVal = null;
        if (fileDetails != null) {
            List<FileDetails> shortList = fileDetails.stream()
                    .filter(fd -> fileName.equalsIgnoreCase(fd.getName()))
                    .collect(Collectors.toList());

            if (shortList != null && shortList.size() > 0) {
                retVal = shortList.get(0);
            }
        }
        return retVal;
    }

    public Set<String> getFileNames() {
        return fileDetails.stream().map(FileDetails::getName).collect(Collectors.toSet());
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getErrors() {
        if (this.errors == null) {
            this.errors = new HashMap<String, String>();
        }
        return errors;
    }

    public boolean hasError() {
        if (this.errors != null && this.errors.size() > 0) {
            return true;
        }
        return false;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public boolean isAttachReplace() {
        return attachReplace;
    }

    public void setAttachReplace(boolean attachReplace) {
        this.attachReplace = attachReplace;
    }

    public String getServiceType() {

        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    public String getZipFileSize() {
        return zipFileSize;
    }

    public void setZipFileSize(String zipFileSize) {
        this.zipFileSize = zipFileSize;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public String getPcbClass() {
        return pcbClass;
    }

    public void setPcbClass(String pcbClass) {
        this.pcbClass = pcbClass;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public boolean isItar() {
        return itar;
    }

    public void setItar(boolean itar) {
        this.itar = itar;
    }

    public boolean getItar() {
        return itar;
    }

    public String getDesignSpecification() {
        return designSpecification;
    }

    public void setDesignSpecification(String designSpecification) {
        this.designSpecification = designSpecification;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isNewProject() {
        return newProject;
    }

    public void setNewProject(boolean newProject) {
        this.newProject = newProject;
    }

    public Differences getDifferences() {
        return differences;
    }

    public void setDifferences(Differences differences) {
        this.differences = differences;
    }

    public boolean isNofly() {
        return nofly;
    }

    public void setNofly(boolean nofly) {
        this.nofly = nofly;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    /**
     * @return the fileChanges
     */
    public List<FileChange> getFileChanges() {
        return fileChanges;
    }

    /**
     * @param fileChanges the fileChanges to set
     */
    public void setFileChanges(List<FileChange> fileChanges) {
        this.fileChanges = fileChanges;
    }

}
