package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "FileChange", description = "FileChange for showing differences")
@JsonInclude(Include.NON_NULL)
public class FileChange  implements Serializable {


	private static final long serialVersionUID = -8354162800018803762L;
	private String fileName;
	private Change action; 
	private List<AttributeChange> attributes;
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the action
	 */
	public Change getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(Change action) {
		this.action = action;
	}
	/**
	 * @return the attributes
	 */
	public List<AttributeChange> getAttributes() {
		return attributes;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<AttributeChange> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(AttributeChange attribute) {
		if(this.attributes == null) {
			this.attributes = new ArrayList<AttributeChange>();
		}
		this.attributes.add(attribute);
	}

}
