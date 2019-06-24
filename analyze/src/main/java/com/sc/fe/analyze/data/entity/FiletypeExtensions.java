package com.sc.fe.analyze.data.entity;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Hemant
 */
@Table(value = "filetype_extensions")
public class FiletypeExtensions implements Serializable {

    private static final long serialVersionUID = -2878322995481840187L;
	
    @PrimaryKey
    private FiletypeExtensionsPK key;
    private Set<String> extensions;

    public FiletypeExtensionsPK getKey() {
        if(key == null){
            key = new FiletypeExtensionsPK();
        }
        return key;
    }

    public void setKey(FiletypeExtensionsPK key) {
        this.key = key;
    }

    public Set<String> getExtensions() {
        return extensions;
    }

    public void setExtensions(Set<String> extensions) {
        this.extensions = extensions;
    }
    
      public String getFileType(){
        return getKey().getFiletype();
    }
    
    public void setFileType(String filetype){
        
        if(key==null){
            key= new FiletypeExtensionsPK();
        }
        
        key.setFiletype(filetype);
    }

}
