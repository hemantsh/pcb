package com.sc.fe.analyze.data.entity;

import java.util.Map;
import java.util.Set;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Report", description = "Table Structure Report")
@Table(value = "report")
public class Report {

    @PrimaryKey
    private ReportPK key;

    @Column(value = "customer_id")
    private String customerId;
    @Column(value = "service_id")
    private int serviceTypeId;
    private String summary;
    @Column(value = "filetype_filename")
    private Map<Integer, String> fileTypeToFileNameMapping;
    private Set<String> errors;
    private Set<String> notes;
    @Column(value = "project_files")
    private Set<String> projectFiles;
    private String status;

    /**
     * Gets the ProjectId
     *
     * @return the projectId
     */
    public String getProjectId() {
        return getKey().getProjectId();
    }

    /**
     * Sets the ProjectId into the variable projectId.
     *
     * @param projectId takes projectId and sets it
     */
    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public int getVersion() {
        return getKey().getVersion();
    }

    /**
     * Sets the version into the variable version.
     *
     * @param version takes version and sets it
     */
    public void setVersion(int version) {
        this.getKey().setVersion(version);
    }

    /**
     * Gets CustomerId
     *
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the CustomerId into the customerId variable.
     *
     * @param customerId takes customerId and sets it
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the serviceTypeId.
     *
     * @return the serviceTypeId
     */
    public int getServiceTypeId() {
        return serviceTypeId;
    }

    /**
     * Sets the serviceTypeid into the variable serviceTypeId.
     *
     * @param serviceTypeId takes serviceTypeId and sets it
     */
    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    /**
     * Gets the Summary.
     *
     * @return the summary of the report.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the Summary into the variable summary.
     *
     * @param summary sets the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
    /**
     * Gets the File
     * @return the fileTypeToFileNameMapping
     */
    public Map<Integer, String> getFileTypeToFileNameMapping() {
        return fileTypeToFileNameMapping;
    }

    public void setFileTypeToFileNameMapping(Map<Integer, String> fileTypeToFileNameMapping) {
        this.fileTypeToFileNameMapping = fileTypeToFileNameMapping;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    public Set<String> getNotes() {
        return notes;
    }

    public void setNotes(Set<String> notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReportPK getKey() {
        if (key == null) {
            key = new ReportPK();
        }
        return key;
    }

    public void setKey(ReportPK key) {
        this.key = key;
    }

    public Set<String> getProjectFiles() {
        return projectFiles;
    }

    public void setProjectFiles(Set<String> projectFiles) {
        this.projectFiles = projectFiles;
    }

}
