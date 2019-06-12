/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.to;

import com.sc.fe.analyze.data.entity.FiletypeExtensionsPK;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author pc
 */
public class FileTypeExtensions {

    private FiletypeExtensionsPK key;
    private String extensions;

    public FiletypeExtensionsPK getKey() {
        return key;
    }

    public void setKey(FiletypeExtensionsPK key) {
        this.key = key;
    }

    public UUID getId() {
        return getKey().getId();
    }

    public void setId(UUID id) {
        this.getKey().setId(id);
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
                stringExtensions += "," + extn;
            }
        }
        return stringExtensions;
    }
}
