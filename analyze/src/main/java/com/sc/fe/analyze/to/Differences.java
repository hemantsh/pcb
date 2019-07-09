/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.to;

import java.util.List;

/**
 *
 * @author pc
 */
public class Differences {
    private List<FileChange> fileChanges;
    private List<AttributeChange> projectChanges;

    public List<FileChange> getFileChanges() {
        return fileChanges;
    }

    public void setFileChanges(List<FileChange> fileChanges) {
        this.fileChanges = fileChanges;
    }

    public List<AttributeChange> getProjectChanges() {
        return projectChanges;
    }

    public void setProjectChanges(List<AttributeChange> projectChanges) {
        this.projectChanges = projectChanges;
    }
}
