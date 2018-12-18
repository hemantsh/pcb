package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value="service_files")
public class ServiceFiles {
	
	@PrimaryKey
	private ServiceFilesPK key;
	
 	private String service;
 	private String file;
 	
	public int getServiceId() {
		return key.getServiceId();
	}
	public void setServiceId(int serviceId) {
		this.key.setServiceId(serviceId);
	}
	public int getFiletypeId() {
		return key.getFiletypeId();
	}
	public void setFiletypeId(int filetypeId) {
		this.key.setFiletypeId(filetypeId);
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
		return key;
	}
	public void setKey(ServiceFilesPK key) {
		this.key = key;
	}
 	
 	
 	
}
