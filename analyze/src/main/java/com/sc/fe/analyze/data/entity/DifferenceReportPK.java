/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "DifferenceReport Primary Key", description = "Primary Key Structure of DifferenceReport")
@PrimaryKeyClass
public class DifferenceReportPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = "project_id")
    @ApiModelProperty("Primary Key")
    private String projectId;

    /**
     * Gets the projectId
     *
     * @return the projectId
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
