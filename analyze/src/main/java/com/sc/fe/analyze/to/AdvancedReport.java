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
 * @author hemant
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
	
	
	public CustomerInputs getCustomerInputs() {
		return customerInputs;
	}

	public void setCustomerInputs(CustomerInputs customerInputs) {
		this.customerInputs = customerInputs;
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

	public String getOdbMatrix() {
		return odbMatrix;
	}

	public void setOdbMatrix(String odbMatrix) {
		this.odbMatrix = odbMatrix;
	}
	
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
	
	@JsonIgnore
	public Set<String> getAllFileNames() {
		if(fileDetails == null) {
			return null;
		}
		return fileDetails.stream()
				.map(FileDetails::getName)
				.collect(Collectors.toCollection(TreeSet::new));
	}

	public Set<String> getErrors() {
		return errors;
	}

	public void setErrors(Set<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		if(errors == null) {
			errors = new HashSet<String>();
		}
		errors.add(error);
	}
	
	public void addAdditionalNote(String note) {
		if(additionalNotes == null) {
			additionalNotes = new HashSet<String>();
		}
		additionalNotes.add(note);
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}

	public Set<String> getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(Set<String> additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public Set<String> getExctractedFileNames() {
		return exctractedFileNames;
	}

	public void setExctractedFileNames(Set<String> exctractedFileNames) {
		this.exctractedFileNames = exctractedFileNames;
	}

	public Map<String, Set<String>> getFilePurposeToNameMapping() {
		return filePurposeToNameMapping;
	}

	public void setFilePurposeToNameMapping(Map<String, Set<String>> filePurposeToNameMapping) {
		this.filePurposeToNameMapping = filePurposeToNameMapping;
	}
}
