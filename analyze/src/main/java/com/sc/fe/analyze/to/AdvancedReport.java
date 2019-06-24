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

import io.swagger.annotations.ApiModel;

/**
 * @author Hemant
 */
@JsonInclude(Include.NON_EMPTY)
@ApiModel(value = "AdvanceReport", description = "Table Structure of AdvanceReport")

public class AdvancedReport implements Serializable {

    private static final long serialVersionUID = -3742223075854910617L;

    private String projectId;
    private String RNumber;
    private CustomerInformation customerInformation;
    private PCBInformation boardInfo;
    
    private List<FileDetails> fileDetails;   
    private String summary;
    /**
     * Validation status after running validations
     */
    private String validationStatus;
    /**
     * All errors found in the files
     */
    private Set<String> errors;
    /**
     * Files found in the zip file
     */
    private Set<String> exctractedFileNames;

    
    public void addFileDetail(FileDetails fileDetail) {
        if (fileDetails == null) {
            fileDetails = new ArrayList<FileDetails>();
        }
        fileDetails.add(fileDetail);
    }

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

    @JsonIgnore
    public Set<String> getAllFileNames() {
        if (fileDetails == null) {
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
        if (errors == null) {
            errors = new HashSet<String>();
        }
        errors.add(error);
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

    public Set<String> getExctractedFileNames() {
        return exctractedFileNames;
    }

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
