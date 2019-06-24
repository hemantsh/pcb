package com.sc.fe.analyze.data.entity;

import java.io.Serializable;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Hemant
 */
@Table(value = "service_filetypes")
public class ServiceFiletypes implements Serializable {
    
    private static final long serialVersionUID = 1L;
     
    @PrimaryKey
    private ServiceFiletypesPK key;
    
    public ServiceFiletypesPK getKey() {
        return key;
    }
    
    public void setKey(ServiceFiletypesPK key) {
        this.key = key;
    }
    
    public String getFileType(){
        return getKey().getFiletype();
    }
    
    public void setFileType(String filetype){
        
        if(key==null){
            key= new ServiceFiletypesPK();
        }
        
        key.setFiletype(filetype);
    }
    
}
