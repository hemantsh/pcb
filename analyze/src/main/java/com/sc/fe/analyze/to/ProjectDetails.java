package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ProjectDetails implements Serializable {

    private static final long serialVersionUID = 7229557964688858057L;

    private String projectId;
    private String version;
    private boolean attachReplace;
    private boolean newProject;
    private String serviceType;
    private String zipFileName;
    private String zipFileSize;
    private int layers;
    private Date modifiedDate;
    private Date createDate;
    private String pcbClass;   
    private String boardType;
    private boolean itar;
    private Map<Integer, Integer> turnTimeQuantity;
    private String designSpecification;
    private String customerId;
    private String emailAddress;

    private Map<String, String> errors; //Errors in (key:value) form

    /**
     * Details about the files we found while processing
     */
    private List<FileDetails> fileDetails = new ArrayList<FileDetails>();

    /**
     * It returns the date of the file on which the file was modified/update
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * It sets the date of the file on which the file was modified/update
     *
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * It returns the date of the file on which the file was created
     *
     * @return the date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * It sets the date of the file on which the file was created into the
     * createDate instance variable
     *
     * @param createDate it shows current date and time
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @return the projectId of the project
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * It sets the projectId of the project into the projectId instance variable
     *
     * @param projectId the projectId to set
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     *
     * @return the list of fileDetails
     */
    public List<FileDetails> getFileDetails() {
        return fileDetails;
    }

    /**
     * It sets the list of fileDetails into the fileDetails instance variable
     *
     * @param fileDetails the list of fileDetails to set
     */
    public void setFileDetails(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
    }

    /**
     * It sets the fileDetail into the fileDetails instance variable
     *
     * @param fileDetail the fileDetails to set
     */
    public void addFileDetail(FileDetails fileDetail) {
        if (fileDetails == null) {
            fileDetails = new ArrayList<FileDetails>();
        }
        fileDetails.add(fileDetail);
    }

    /**
     * It gets all the fileNames which stored in the database
     *
     * @return set of all fileNames
     */
    @JsonIgnore
    public Set<String> getAllFileNames() {
        if (fileDetails == null) {
            return null;
        }
        return fileDetails.stream()
                .map(FileDetails::getName)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    /**
     * It retrieves the fileDetails from the database of the file
     *
     * @param fileName the name of the file
     * @return the fileDetails of the file from the database
     */
    public FileDetails getFileDetails(String fileName) {
        FileDetails retVal = null;

        List<FileDetails> shortList = fileDetails.stream()
                .filter(fd -> fileName.equalsIgnoreCase(fd.getName()))
                .collect(Collectors.toList());

        if (shortList != null && shortList.size() > 0) {
            retVal = shortList.get(0);
        }
        return retVal;
    }

    /**
     * It gets all the fileNames which stored in the database
     *
     * @return the set of fileNames
     */
    public Set<String> getFileNames() {
        return fileDetails.stream().map(FileDetails::getName).collect(Collectors.toSet());
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the set of errors in Map(key,value) form
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * It sets the errors in the Map(key,value) pair
     *
     * @param errors the errors to set
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * @return the attachReplace
     */
    public boolean isAttachReplace() {
        return attachReplace;
    }

    /**
     * @param attachReplace the attachReplace to set
     */
    public void setAttachReplace(boolean attachReplace) {
        this.attachReplace = attachReplace;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * It sets the serviceType of file
     *
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the zipFileName
     */
    public String getZipFileName() {
        return zipFileName;
    }

    /**
     * @param zipFileName the zipFileName to set
     */
    public void setZipFileName(String zipFileName) {
        this.zipFileName = zipFileName;
    }

    /**
     * @return the zipFileSize
     */
    public String getZipFileSize() {
        return zipFileSize;
    }

    /**
     * It sets the zipFileSize of zipFile into the zipFileSize instance variable
     *
     * @param zipFileSize the zipFileSize to set
     */
    public void setZipFileSize(String zipFileSize) {
        this.zipFileSize = zipFileSize;
    }

    /**
     * @return the layer Sequence
     */
    public int getLayers() {
        return layers;
    }

    /**
     * It sets the layer Sequence of file into the layers instance variable
     *
     * @param layers the layers to set
     */
    public void setLayers(int layers) {
        this.layers = layers;
    }

    /**
     * @return the pcbClass
     */
    public String getPcbClass() {
        return pcbClass;
    }

    /**
     * @param pcbClass the pcbClass to set
     */
    public void setPcbClass(String pcbClass) {
        this.pcbClass = pcbClass;
    }

    /**
     * @return the boardType of the file
     */
    public String getBoardType() {
        return boardType;
    }

    /**
     * @param boardType the boardType of the to set
     */
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    /**
     * @return the itar
     */
    public boolean isItar() {
        return itar;
    }

    /**
     * @param itar the itar to set
     */
    public void setItar(boolean itar) {
        this.itar = itar;
    }

    /**
     *
     * @return the itar value
     */
    public boolean getItar() {
        return itar;
    }

    /**
     * @return the turnTimeQuantityList
     */
    public Map<Integer, Integer> getTurnTimeQuantity() {
        return turnTimeQuantity;
    }

    /**
     * @param turnTimeQuantityList the turnTimeQuantityList to set
     */
    public void setTurnTimeQuantity(Map<Integer, Integer> turnTimeQuantity) {
        this.turnTimeQuantity = turnTimeQuantity;
    }

    /**
     * @return the designSpecification of the file
     */
    public String getDesignSpecification() {
        return designSpecification;
    }

    /**
     * @param designSpecification the designSpecification to set
     */
    public void setDesignSpecification(String designSpecification) {
        this.designSpecification = designSpecification;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * It sets the customerId of the customer into the customerId instance
     * variable
     *
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * It sets the email of the customer into the emailAddress instance variable
     *
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the newProject
     */
    public boolean isNewProject() {
        return newProject;
    }

    /**
     * @param newProject the newProject to set
     */
    public void setNewProject(boolean newProject) {
        this.newProject = newProject;
    }
}
