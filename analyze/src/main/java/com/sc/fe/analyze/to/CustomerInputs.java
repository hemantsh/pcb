package com.sc.fe.analyze.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author Hemant
 */
@JsonInclude(Include.NON_EMPTY)
public class CustomerInputs implements Serializable {
	
	private static final long serialVersionUID = 1388568635843885556L;
	
    /**
     *
     */
    public CustomerInputs() {
		super();
	}
	
	private int quantity;
	private int turnTime;
	
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
	
	private String emailAddress;
	
	private String zipFileName;
	private String zipFileSize;
	
    /**
     *
     * @return the projectId from the database in the string format
     */
    public String getProjectId() {
		return projectId;
	}

    /**
     *
     * @param projectId - to set the projectId in the database
     */
    public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

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
     * @return the serviceType from the database in the string format
     */
    public String getServiceType() {
		return serviceType;
	}

    /**
     *
     * @param serviceType - set the serviceType in the database
     */
    public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

    /**
     *
     * @return the ZIP filename from the database in the string format
     */
    public String getZipFileName() {
		return zipFileName;
	}

    /**
     *
     * @param zipFileName - set the ZIP fileName in the database
     */
    public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

    /**
     *
     * @return the ZIP fileSize from the database in the string format
     */
    public String getZipFileSize() {
		return zipFileSize;
	}

    /**
     *
     * @param zipFileSize - set the ZIP fileSize in the database
     */
    public void setZipFileSize(String zipFileSize) {
		this.zipFileSize = zipFileSize;
	}

    /**
     *
     * @return the quantity in integer format
     */
    public int getQuantity() {
		return quantity;
	}

    /**
     *
     * @param quantity - set the quantity 
     */
    public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /**
     *
     * @return the turnTime
     */
    public int getTurnTime() {
		return turnTime;
	}

    /**
     *
     * @param turnTime - set the turnTime 
     */
    public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}
	
}
