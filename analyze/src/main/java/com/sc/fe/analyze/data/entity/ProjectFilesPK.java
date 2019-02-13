/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "project_files Primary Key", description = "Combined Primary Key Structure of ProjectFiles")
@PrimaryKeyClass
public class ProjectFilesPK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 0000;
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = "project_id")
    private String projectId;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.PARTITIONED, name = "version")
    private UUID version;
    @ApiModelProperty("First Primary Key")

    @PrimaryKeyColumn(ordinal = 2, type = PrimaryKeyType.CLUSTERED, name = "name", ordering = Ordering.DESCENDING)
    private String name;

    @ApiModelProperty("Second Primary Key")
    /**
     * Gets the ProjectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the ProjectId into the projectId.
     *
     * @param projectId sets the projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * Gets the Version.
     *
     * @return
     */
    public UUID getVersion() {
        return version;
    }

    /**
     * Sets the version into the variable version
     *
     * @param version sets the version
     */
    public void setVersion(UUID version) {
        this.version = version;
    }

    /**
     * Gets the Name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name into the variable name.
     *
     * @param name takes name and sets it
     */
    public void setName(String name) {
        this.name = name;
    }
}
