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
     * All errors found in the files
     */
    private Set<String> errors;
    /**
     * Files found in the zip file
     */
    private Set<String> exctractedFileNames;

    /**
     * Add the fileDetails in the fileDetails instance variable
     *
     * @param fileDetail Set the fileDetails
     */
    public void addFileDetail(FileDetails fileDetail) {
        if (fileDetails == null) {
            fileDetails = new ArrayList<FileDetails>();
        }
        fileDetails.add(fileDetail);
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
     * @return the set of all fileNames
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
     *
     * @return the set of errors
     */
    public Set<String> getErrors() {
        return errors;
    }

    /**
     * Sets the errors in the database found in the file
     *
     * @param errors set of errors
     */
    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    /**
     * Add the error in the database of the file
     *
     * @param error error found in the file
     */
    public void addError(String error) {
        if (errors == null) {
            errors = new HashSet<String>();
        }
        errors.add(error);
    }

    /**
     * Gets the summary of the report
     *
     * @return summary from the database
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of the report into the summary instance variable
     *
     * @param summary summary of the report
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return the validationStatus from the database
     */
    public String getValidationStatus() {
        return validationStatus;
    }

    /**
     * Sets the validationStatus after running the validations
     *
     * @param validationStatus validationStatus after run the Validations
     */
    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    /**
     * Gets the set of FileNames from the database
     *
     * @return the set of FileNames from the database
     */
    public Set<String> getExctractedFileNames() {
        return exctractedFileNames;
    }

    /**
     * It sets all the fileNames in the database
     *
     * @param exctractedFileNames set of exctractedFileNames when unzip the zip
     * file
     */
    public void setExctractedFileNames(Set<String> exctractedFileNames) {
        this.exctractedFileNames = exctractedFileNames;
    }

    /**
     * Gets the Board Information
     *
     * @return the board Information
     */
    public PCBInformation getBoardInfo() {
        return boardInfo;
    }

    /**
     * Sets the Board Information of the PCB
     *
     * @param boardInfo set the board Information
     */
    public void setBoardInfo(PCBInformation boardInfo) {
        this.boardInfo = boardInfo;
    }

    /**
     * Gets the Project Id
     *
     * @return the project Id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the projectId into the projectId instance variable
     *
     * @param projectId projectId of the Report
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the RNumber from the RNumber instance variable
     *
     * @return the RNumber
     */
    public String getRNumber() {
        return RNumber;
    }

    /**
     * Sets the RNumber into the RNumber instance variable
     *
     * @param rNumber set the rNumber
     */
    public void setRNumber(String rNumber) {
        RNumber = rNumber;
    }

    /**
     * Gets the customerInformation from the customerInformation instance
     * variable
     *
     * @return the CustomerInformation
     */
    public CustomerInformation getCustomerInformation() {
        return customerInformation;
    }

    /**
     * sets the CustomerInformation into the customerInformation instance
     * variable
     *
     * @param customerInformation sets the customerInformation
     */
    public void setCustomerInformation(CustomerInformation customerInformation) {
        this.customerInformation = customerInformation;
    }

    /**
     * Gets the fileDetails from the fileDetails instance variable
     *
     * @return the list of fileDetails
     */
    public List<FileDetails> getFileDetails() {
        return fileDetails;
    }

    /**
     * Sets the fileDetails into the fileDetails instance variable
     *
     * @param fileDetails the list of fileDetails
     */
    public void setFileDetails(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
    }

}
