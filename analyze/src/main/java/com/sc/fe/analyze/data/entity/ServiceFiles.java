package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@ApiModel(value="ServiceFiles",description="Table Structure of ServiceFiles")
@Table(value="service_files")
public class ServiceFiles {
	
	@PrimaryKey
	private ServiceFilesPK key;
	
 	private String service;
 	private String file;
 	
	public int getServiceId() {
		return getKey().getServiceId();
	}
	public void setServiceId(int serviceId) {
		this.getKey().setServiceId(serviceId);
	}
	public int getFiletypeId() {
		return getKey().getFiletypeId();
	}
	public void setFiletypeId(int filetypeId) {
		this.getKey().setFiletypeId(filetypeId);
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public ServiceFilesPK getKey() {
		if(key == null) {
			key = new ServiceFilesPK();
		}
		return key;
	}
	public void setKey(ServiceFilesPK key) {
		this.key = key;
	}
 	
 	
 	
}
