package com.sc.fe.analyze.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author Hemant
 */
@JsonInclude(Include.NON_EMPTY)
public class CustomerInformation implements Serializable {
	//TODO: remove this class
	private static final long serialVersionUID = 1388568635843885556L;
	
    /**
     *
     */
    public CustomerInformation() {
		super();
	}
	
	
	private String customerName;
	
	private String customerId;
	
	private String emailAddress;
	
	private String projectId;
	

    /**
     *
     * @return the customerId from the database in the string format
     */
    public String getCustomerId() {
		return customerId;
	}

    /**
     *
     * @param customerId - set the customerId in the database
     */
    public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


    /**
     *
     * @return the emailAddress from the database in a string format
     */
    public String getEmailAddress() {
		return emailAddress;
	}

    /**
     *
     * @param emailAddress - set the emailAddress in the database
     */
    public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	
}
