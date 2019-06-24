package com.sc.fe.analyze.data.entity;

import java.util.Map;
import java.util.Set;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * 
 * @author Hemant
 */
@ApiModel(value = "Report", description = "Table Structure Report")
@Table(value = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public String getProjectId() {
        return getKey().getProjectId();
    }

    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);;
    }

    public int getVersion() {
        return getKey().getVersion();
    }

    public void setVersion(int version) {
        this.getKey().setVersion(version);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

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
