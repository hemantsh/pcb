package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sc.fe.analyze.util.ErrorCodes;

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

    private Set<ErrorCodes> errorCodes;

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

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if (errors == null) {
            errors = new HashSet<String>();
        }
        errors.add(error);
    }

    public ProjectDetails getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetails projectDetail) {
        this.projectDetail = projectDetail;
    }

    public Set<ErrorCodes> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(Set<ErrorCodes> errorCodes) {
        this.errorCodes = errorCodes;
    }

    public void addErrorCode(ErrorCodes code) {
        if (errorCodes == null) {
            errorCodes = new HashSet<ErrorCodes>();
        }
        errorCodes.add(code);
    }
}
