package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author Hemant
 */
@JsonInclude(Include.NON_EMPTY)
public class Report implements Serializable {

    private static final long serialVersionUID = -503593643675215988L;

    /**
     * Default Constructor
     */
    public Report() {
        super();
    }

    /**
     * The initial input that was processed, populated and validated
     */
    private ProjectDetails projectDetail;
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
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary - the summary to set
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
     * @param validationStatus - the validationStatus to set
     */
    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    /**
     * @return the errors
     */
    public Set<String> getErrors() {
        return errors;
    }

    /**
     * @param errors - the errors to set
     */
    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    /**
     *
     * @param error - the error to add in the errors instance variable
     */
    public void addError(String error) {
        if (errors == null) {
            errors = new HashSet<String>();
        }
        errors.add(error);
    }

    /**
     * @return the exctractedFileNames
     */
    public Set<String> getExctractedFileNames() {
        return exctractedFileNames;
    }

    /**
     * @param exctractedFileNames - the exctractedFileNames to set
     */
    public void setExctractedFileNames(Set<String> exctractedFileNames) {
        this.exctractedFileNames = exctractedFileNames;
    }

	public ProjectDetails getProjectDetail() {
		return projectDetail;
	}

	public void setProjectDetail(ProjectDetails projectDetail) {
		this.projectDetail = projectDetail;
	}

}
