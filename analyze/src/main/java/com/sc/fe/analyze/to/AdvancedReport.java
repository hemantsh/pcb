package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author hemant
 *
 */
public class AdvancedReport implements Serializable {
	
	private static final long serialVersionUID = -3742223075854910617L;
	
	/**
	 * Customer inputs
	 */
	private CustomerInputs customerInputs;
	/**
	 * Details about the files we found while processing
	 */
	private List<FileDetails> fileDetails;
	
	private String odbMatrix;
	
	public CustomerInputs getCustomerInputs() {
		return customerInputs;
	}

	public void setCustomerInputs(CustomerInputs customerInputs) {
		this.customerInputs = customerInputs;
	}

	public List<FileDetails> getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(List<FileDetails> fileDetails) {
		this.fileDetails = fileDetails;
	}

	public void addFileDetail(FileDetails fileDetail) {
		if(fileDetails == null) {
			fileDetails = new ArrayList<FileDetails>();
		}
		fileDetails.add(fileDetail);
	}

	public String getOdbMatrix() {
		return odbMatrix;
	}

	public void setOdbMatrix(String odbMatrix) {
		this.odbMatrix = odbMatrix;
	}
	
	public FileDetails getFileDetails(String fileName) {
		FileDetails retVal = null;
		
		List<FileDetails> shortList = fileDetails.stream()
				.filter(  fd -> fileName.equalsIgnoreCase(fd.getName()) )
				.collect(Collectors.toList());
		
		if(shortList != null && shortList.size() > 0) {
			retVal = shortList.get(0);
		}
		return retVal;
	}
	
	public Set<String> getAllFileNames() {
		if(fileDetails == null) {
			return null;
		}
		return fileDetails.stream()
				.map(FileDetails::getName)
				.collect(Collectors.toCollection(TreeSet::new));
	}
}
