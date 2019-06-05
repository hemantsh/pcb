/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import java.util.Set;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author pc
 */
@Table(value = "filetype_extensions")
public class FiletypeExtensions {

    @PrimaryKey
    private String file_type;
    private Set<String> extensions;

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type.toLowerCase();
    }

    public Set<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Set<String> extensions) {
        this.extensions = extensions;
    }

}
