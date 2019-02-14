/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Project Primary Key", description = "Combined Primary Key Structure of Project")
@PrimaryKeyClass
public class ProjectPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = "project_id")
    @ApiModelProperty("First Primary Key")
    private String projectId;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name = "version", ordering = Ordering.DESCENDING)
    @ApiModelProperty("Second Primary Key")
    private UUID version;

    /**
     * Gets the version.
     *
     * @return
     */
    public UUID getVersion() {
        return version;
    }

    /**
     * Sets the version into the variable version.
     *
     * @param version takes version and sets it
     */
    public void setVersion(UUID version) {
        this.version = version;
    }

    /**
     * Gets the ProjectId
     *
     * @return
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Sets the ProjectId into the variable projectId.
     *
     * @param projectId takes projectId and sets it
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
