/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "difference_report", description = "Table Structure difference_report")
@Table(value = "difference_report")
public class DifferenceReport {

    @PrimaryKey(value = "project_id")
    private String projectId;
    @Column(value = "version")
    private UUID version;
    @Column(value = "differences")
    private Set<String> differences;

    /**
     * Gets the projectId
     *
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the projectId in the projectId instance variable.
     *
     * @param projectId Sets the projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the version
     *
     * @return the version
     */
    public UUID getVersion() {
        return version;
    }

    /**
     * Sets the version in the version instance variable
     *
     * @param version Sets the version
     */
    public void setVersion(UUID version) {
        this.version = version;
    }

    /**
     * Gets the differences from differences instance variable
     *
     * @return the set of differences
     */
    public Set<String> getDifferences() {
        return differences;
    }

    /**
     * Sets the differences in the differences instance variable
     *
     * @param differences Sets the differences
     */
    public void setDifferences(Set<String> differences) {
        this.differences = differences;
    }
}
