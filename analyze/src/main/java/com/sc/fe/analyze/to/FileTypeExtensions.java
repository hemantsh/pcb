/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.to;

import java.util.Set;

/**
 *
 * @author pc
 */
public class FileTypeExtensions {

    private String file_type;
    private String extensions;

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public String extnToString(Set<String> extensions) {
        String stringExtensions = "";
        for (String extn : extensions) {
            if (stringExtensions.isEmpty()) {
                stringExtensions = extn;
            } else {
                stringExtensions +=  ","+extn;
            }
        }
        return stringExtensions;
    }
}
