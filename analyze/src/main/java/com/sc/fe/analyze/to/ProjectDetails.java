package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class ProjectDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7229557964688858057L;

	private String projectId;
	private String version;
	
	private String serviceType;
	private String zipFileName;
	private String zipFileSize;
	private int layers;
    private String pcbClass;
    private String boardType;
    private boolean itar;
    private Map<Integer, Integer> turnTimeQuantity;
    private String designSpecification;
    
    private String customerId;
	private String emailAddress;

    private Map<String, String> errors;
    
	/**
	 * Details about the files we found while processing
	 */
	private List<FileDetails> fileDetails = new ArrayList<FileDetails>();
	
	private boolean attachReplace;
	private boolean newProject;
	
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
		if(fileDetails == null) {
			fileDetails = new ArrayList<FileDetails>();
		}
		fileDetails.add(fileDetail);
	}
	
	public Set<String> getFileNames() {
		return fileDetails.stream().map( FileDetails :: getName ).collect( Collectors.toSet() );
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
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}
	/**
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
	 * @param zipFileSize the zipFileSize to set
	 */
	public void setZipFileSize(String zipFileSize) {
		this.zipFileSize = zipFileSize;
	}
	/**
	 * @return the layers
	 */
	public int getLayers() {
		return layers;
	}
	/**
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
	 * @return the boardType
	 */
	public String getBoardType() {
		return boardType;
	}
	/**
	 * @param boardType the boardType to set
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
	 * @return the designSpecification
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
