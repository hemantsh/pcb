
package com.sc.fe.analyze.to;

import java.io.Serializable;

/**
 *
 * @author Hemant
 */
public class FileTypeExtensions implements Serializable{

    private static final long serialVersionUID = -3742223075854910617L;
    
    private String extensions;
    private String id;
    private String file_type;

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExtensions() {
        return extensions;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

}
