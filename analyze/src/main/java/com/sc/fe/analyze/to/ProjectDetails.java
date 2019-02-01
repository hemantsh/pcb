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
	
	private CustomerInformation customerInformation;
	
	private PCBInformation boardInfo;
	

    private Map<String, String> errors;
    
	/**
	 * Details about the files we found while processing
	 */
	private List<FileDetails> fileDetails = new ArrayList<FileDetails>();
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public CustomerInformation getCustomerInformation() {
		return customerInformation;
	}
	public void setCustomerInformation(CustomerInformation customerInformation) {
		this.customerInformation = customerInformation;
	}
	public PCBInformation getBoardInfo() {
		return boardInfo;
	}
	public void setBoardInfo(PCBInformation boardInfo) {
		this.boardInfo = boardInfo;
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
}
