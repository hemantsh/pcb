package com.sc.fe.analyze.data.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class ServiceFilesPK implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133581623671470779L;
	
	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name="service_id")
	private int serviceId;
	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name="filetype_id")
    private int filetypeId;
	
	@Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Integer.valueOf(serviceId).hashCode();
      result = prime * result + Integer.valueOf(filetypeId).hashCode();
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      
      ServiceFilesPK other = (ServiceFilesPK) obj;
      if(other.filetypeId != filetypeId) {
    	  return false;
      }
      if(other.serviceId != serviceId) {
    	  return false;
      }

      return true;
    }

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getFiletypeId() {
		return filetypeId;
	}

	public void setFiletypeId(int filetypeId) {
		this.filetypeId = filetypeId;
	}
    
	
}
