package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Hemant
 *
 */
public class AdvancedReport implements Serializable {
	
	private static final long serialVersionUID = -3742223075854910617L;
	
	/**
	 * Customer inputs 
	 */
	private CustomerInputs customerInputs;
	/**
	 * Details about the files we found while processing
	 */
	private List<FileDetails> fileDetails;
	
	private String odbMatrix;
	
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
	 * Any notes about package
	 */
	private Set<String> additionalNotes;
	/**
	 * Files found in the zip file 
	 */
	private Set<String> exctractedFileNames;
	
	/**
	 * Mapping of file business purpose with the file name
	 */
	private Map<String, Set<String> > filePurposeToNameMapping;
	
    /**
     *
     * @return the customerInputs
     */
    public CustomerInputs getCustomerInputs() {
		return customerInputs;
	}

    /**
     *
     * @param customerInputs - set the customerInputs in a database
     */
    public void setCustomerInputs(CustomerInputs customerInputs) {
		this.customerInputs = customerInputs;
	}

    /**
     *
     * @return the fileDetails from the database
     */
    public List<FileDetails> getFileDetails() {
		return fileDetails;
	}

    /**
     *
     * @param fileDetails - set the fileDetails in the database
     */
    public void setFileDetails(List<FileDetails> fileDetails) {
		this.fileDetails = fileDetails;
	}

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
     * @return the String
     */
    public String getOdbMatrix() {
		return odbMatrix;
	}

    /**
     *
     * @param odbMatrix - set the odbMatrix
     */
    public void setOdbMatrix(String odbMatrix) {
		this.odbMatrix = odbMatrix;
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
     * @param note - add  note in the database
     */
    public void addAdditionalNote(String note) {
		if(additionalNotes == null) {
			additionalNotes = new HashSet<String>();
		}
		additionalNotes.add(note);
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
     * @return additionalNotes in set of string from the database
     */
    public Set<String> getAdditionalNotes() {
		return additionalNotes;
	}

    /**
     *
     * @param additionalNotes - set additionalNotes in the database
     */
    public void setAdditionalNotes(Set<String> additionalNotes) {
		this.additionalNotes = additionalNotes;
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

    /**
     *
     * @return filePurposeToNameMapping in (key,value) pair from the database
     */
    public Map<String, Set<String>> getFilePurposeToNameMapping() {
		return filePurposeToNameMapping;
	}

    /**
     *
     * @param filePurposeToNameMapping - set filePurposeToNameMapping in (key,value) pair in the database 
     */
    public void setFilePurposeToNameMapping(Map<String, Set<String>> filePurposeToNameMapping) {
		this.filePurposeToNameMapping = filePurposeToNameMapping;
	}
}
