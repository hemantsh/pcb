package com.sc.fe.analyze.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class CustomerInputs implements Serializable {
	
	private static final long serialVersionUID = 1388568635843885556L;
	
	public CustomerInputs() {
		super();
	}
	
	/**
	 * The projectId
	 */
	private String projectId;
	/**
	 * Customer ID
	 */
	private String customerId;
	/**
	 * The service type user is requesting
	 */
	private String serviceType;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
}
