package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private String[] purposeType;
	private String customerSelectedPurposeType;
	private String mimeType;
	private boolean valid;
	
	private String ocrText;
	
	private List<String> attributes; //Attributes in key:value form
	private String otherExtractedInfoRaw;
	private List<String> otherExtractedInfoFormated; //Attributes in key:value form
	
	private List<String> parserNotes;
	
	//internal properties. not to be returned in response
	@JsonIgnore
	private int processCount;
	@JsonIgnore
	private int advancedProcessingCount;
	@JsonIgnore
	private String version;

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
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
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
	 * @return the ocrText
	 */
	public String getOcrText() {
		return ocrText;
	}

	/**
	 * @param ocrText the ocrText to set
	 */
	public void setOcrText(String ocrText) {
		this.ocrText = ocrText;
	}

	/**
	 * @return the attributes
	 */
	public List<String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the otherExtractedInfoRaw
	 */
	public String getOtherExtractedInfoRaw() {
		return otherExtractedInfoRaw;
	}

	/**
	 * @param otherExtractedInfoRaw the otherExtractedInfoRaw to set
	 */
	public void setOtherExtractedInfoRaw(String otherExtractedInfoRaw) {
		this.otherExtractedInfoRaw = otherExtractedInfoRaw;
	}

	/**
	 * @return the otherExtractedInfoFormated
	 */
	public List<String> getOtherExtractedInfoFormated() {
		return otherExtractedInfoFormated;
	}

	/**
	 * @param otherExtractedInfoFormated the otherExtractedInfoFormated to set
	 */
	public void setOtherExtractedInfoFormated(List<String> otherExtractedInfoFormated) {
		this.otherExtractedInfoFormated = otherExtractedInfoFormated;
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
	 * @return the customerSelectedPurposeType
	 */
	public String getCustomerSelectedPurposeType() {
		return customerSelectedPurposeType;
	}

	/**
	 * @param customerSelectedPurposeType the customerSelectedPurposeType to set
	 */
	public void setCustomerSelectedPurposeType(String customerSelectedPurposeType) {
		this.customerSelectedPurposeType = customerSelectedPurposeType;
	}

	/**
	 * @return the processCount
	 */
	public int getProcessCount() {
		return processCount;
	}

	/**
	 * @param processCount the processCount to set
	 */
	public void setProcessCount(int processCount) {
		this.processCount = processCount;
	}

	/**
	 * @return the advancedProcessingCount
	 */
	public int getAdvancedProcessingCount() {
		return advancedProcessingCount;
	}

	/**
	 * @param advancedProcessingCount the advancedProcessingCount to set
	 */
	public void setAdvancedProcessingCount(int advancedProcessingCount) {
		this.advancedProcessingCount = advancedProcessingCount;
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
}
