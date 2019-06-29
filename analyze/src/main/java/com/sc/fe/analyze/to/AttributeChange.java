package com.sc.fe.analyze.to;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "AttributeChange", description = "AttributeChange for showing differences")

public class AttributeChange implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5199166967750716328L;
	private String name;
	private String oldValue;
	private String newValue;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the oldValue
	 */
	public String getOldValue() {
		return oldValue;
	}
	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}
	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
}
