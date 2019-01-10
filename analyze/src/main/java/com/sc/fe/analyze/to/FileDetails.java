package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class FileDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5547755272664009268L;
	
	private String projectId;
	private String name;
	private String version;
	private String fileFormat; //Gerber, odb, drill
	private Map<String,String> attributes; //Attributes in key:value form
	
   	private String[] purposeType;
	private boolean valid;
	private Date modifiedDate;
	private String fileSize;
	
	private List<String> parserNotes;
	private LayersInformation layerInfo;
	
        /**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

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
	 * @return the purposeType
	 */
	public String[] getPurposeType() {
		return purposeType;
	}

	/**
	 * @param purposeType the purposeType to set
	 */
	public void setPurposeType(String[] purposeType) {
		this.purposeType = purposeType;
	}

	/**
	 * @return the valid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the parserNotes
	 */
	public List<String> getParserNotes() {
		return parserNotes;
	}

	/**
	 * @param parserNotes the parserNotes to set
	 */
	public void setParserNotes(List<String> parserNotes) {
		this.parserNotes = parserNotes;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/*public Map<String,Map<String,String>> getfileAttributes() {
		return attrFile;
	}

	public void setfileAttributes(Map<String,String> attributes,String Name)
        {
                this.attrFile.put(Name, attributes);
                System.out.println(this.attrFile);
        }*/
	public Map<String,String> getAttributes()
        {
		return attributes;
	}
        public void setAttributes(Map<String,String> attributes) {
		this.attributes=attributes;
	}

		public LayersInformation getLayerInfo() {
			return layerInfo;
		}

		public void setLayerInfo(LayersInformation layerInfo) {
			this.layerInfo = layerInfo;
		}
}
