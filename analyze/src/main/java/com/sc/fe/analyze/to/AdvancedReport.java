package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemant
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class AdvancedReport implements Serializable {
	
	private static final long serialVersionUID = -3742223075854910617L;
	
	private String projectId;
	
	private String RNumber;
	
	private CustomerInformation customerInformation;
	
	private PCBInformation boardInfo;
	/**
	 * Details about the files we found while processing
	 */
	private List<FileDetails> fileDetails;
	
	/**
	 * Summary of the report
	 */
	private String summary;
	/**
	 * Validation status after running validations
	 */
	private String validationStatus;
	/**
	 * All errors we found in the files
	 */
	private Set<String> errors;
	
	/**
	 * Files found in the zip file 
	 */
	private Set<String> exctractedFileNames;
	
	

    /**
     *
     * @param fileDetail - add the fileDetail in fileDetails instance variable
     */
    public void addFileDetail(FileDetails fileDetail) {
		if(fileDetails == null) {
			fileDetails = new ArrayList<FileDetails>();
		}
		fileDetails.add(fileDetail);
	}

    /**
     *
     * @param fileName - retrieve  fileDetails from specific fileName 
     * @return fileDetails of fileName from the database
     */
    public FileDetails getFileDetails(String fileName) {
		FileDetails retVal = null;
		
		List<FileDetails> shortList = fileDetails.stream()
				.filter(  fd -> fileName.equalsIgnoreCase(fd.getName()) )
				.collect(Collectors.toList());
		
		if(shortList != null && shortList.size() > 0) {
			retVal = shortList.get(0);
		}
		return retVal;
	}
	
    /**
     *
     * @return set of all fileNames which stored in the database
     */
    @JsonIgnore
	public Set<String> getAllFileNames() {
		if(fileDetails == null) {
			return null;
		}
		return fileDetails.stream()
				.map(FileDetails::getName)
				.collect(Collectors.toCollection(TreeSet::new));
	}

    /**
     *
     * @return errors from the database
     */
    public Set<String> getErrors() {
		return errors;
	}

    /**
     *
     * @param errors - set error in the database
     */
    public void setErrors(Set<String> errors) {
		this.errors = errors;
	}

    /**
     *
     * @param error - add error in the database
     */
    public void addError(String error) {
		if(errors == null) {
			errors = new HashSet<String>();
		}
		errors.add(error);
	}
	
	
    /**
     *
     * @return summary from the database
     */
    public String getSummary() {
		return summary;
	}

    /**
     *
     * @param summary - set summary in the database
     */
    public void setSummary(String summary) {
		this.summary = summary;
	}

    /**
     *
     * @return validationStatus from the database
     */
    public String getValidationStatus() {
		return validationStatus;
	}

    /**
     *
     * @param validationStatus - set validationStatus in the database
     */
    public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}


    /**
     *
     * @return exctractedFileNames from the database
     */
    public Set<String> getExctractedFileNames() {
		return exctractedFileNames;
	}

    /**
     *
     * @param exctractedFileNames - set exctractedFileNames in the database
     */
    public void setExctractedFileNames(Set<String> exctractedFileNames) {
		this.exctractedFileNames = exctractedFileNames;
	}

	public PCBInformation getBoardInfo() {
		return boardInfo;
	}

	public void setBoardInfo(PCBInformation boardInfo) {
		this.boardInfo = boardInfo;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRNumber() {
		return RNumber;
	}

	public void setRNumber(String rNumber) {
		RNumber = rNumber;
	}

	public CustomerInformation getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(CustomerInformation customerInformation) {
		this.customerInformation = customerInformation;
	}

	public List<FileDetails> getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(List<FileDetails> fileDetails) {
		this.fileDetails = fileDetails;
	}

}
