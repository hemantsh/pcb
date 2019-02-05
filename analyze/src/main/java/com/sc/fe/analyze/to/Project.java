/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author Hemant
 */
@JsonInclude(Include.NON_EMPTY)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    public Project() {
        super();
    }
    private ProjectDetails projectDetail;

    public ProjectDetails getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(ProjectDetails projectDetail) {
        this.projectDetail = projectDetail;
    }
}
