/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author pc
 */
@Table(value = "service_filetypes")
public class ServiceFiletypes {
    
    @PrimaryKey
    private ServiceFiletypesPK key;
    
    public ServiceFiletypesPK getKey() {
        return key;
    }
    
    public void setKey(ServiceFiletypesPK key) {
        this.key = key;
    }
}
