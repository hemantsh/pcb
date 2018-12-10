package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Report implements Serializable {
	
	private static final long serialVersionUID = -503593643675215988L;
	
	public Report() {
		super();
	}
	
	/**
	 * Customer inputs
	 */
	private CustomerInputs customerInputs;
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
	private List<String> errors;
	/**
	 * Any notes about package
	 */
	private List<String> additionalNotes;
	/**
	 * Files found in the zip file 
	 */
	private List<String> exctractedFileNames;
	
	/**
	 * Mapping of file business purpose with the file name
	 */
	private Map<String, Set<String> > filePurposeToNameMapping;
	
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the validationStatus
	 */
	public String getValidationStatus() {
		return validationStatus;
	}
	/**
	 * @param validationStatus the validationStatus to set
	 */
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	/**
	 * @return the additionalNotes
	 */
	public List<String> getAdditionalNotes() {
		return additionalNotes;
	}
	/**
	 * @param additionalNotes the additionalNotes to set
	 */
	public void setAdditionalNotes(List<String> additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	
	/**
	 * @return the customerInputs
	 */
	public CustomerInputs getCustomerInputs() {
		return customerInputs;
	}
	/**
	 * @param customerInputs the customerInputs to set
	 */
	public void setCustomerInputs(CustomerInputs customerInputs) {
		this.customerInputs = customerInputs;
	}
	/**
	 * @return the filePurposeToNameMapping
	 */
	public Map<String, Set<String>> getFilePurposeToNameMapping() {
		return filePurposeToNameMapping;
	}
	/**
	 * @param filePurposeToNameMapping the filePurposeToNameMapping to set
	 */
	public void setFilePurposeToNameMapping(Map<String, Set<String>> filePurposeToNameMapping) {
		this.filePurposeToNameMapping = filePurposeToNameMapping;
	}
	/**
	 * @return the exctractedFileNames
	 */
	public List<String> getExctractedFileNames() {
		return exctractedFileNames;
	}
	/**
	 * @param exctractedFileNames the exctractedFileNames to set
	 */
	public void setExctractedFileNames(List<String> exctractedFileNames) {
		this.exctractedFileNames = exctractedFileNames;
	}
	
	
}
