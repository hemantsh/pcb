package com.sc.fe.analyze.to;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sc.fe.analyze.to.Directory.FileDetail;

import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "AttachementDetails", description = "Structure of AttachementDetails")
@JsonInclude(Include.NON_NULL)
public class AttachementDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3401889331514724473L;
	private List<Directory> directoryList;
	private Set<String> errorMessages;
	private Set<String> warningMessages;
	private Set<String> infoMessages;
	

	@JsonIgnore
	public int gerberDirectoryCount() {
		int count = 0;
		if( directoryList != null ) {
			for (Directory directory : directoryList) {
				if( directory != null && "GERBER".equalsIgnoreCase(directory.getType())) {
					count++;
				}
			}
		}
		return count;
	}
	
	@JsonIgnore
	public boolean isMultiFormat() {
		boolean retValue = false;
		
		boolean hasGerber = gerberDirectoryCount() > 0;
		boolean hasODB = odbDirectoryCount() > 0;
		boolean hasIPC = ipcDirectoryCount() > 0;
		
		if(hasGerber) {
			retValue = hasIPC || hasODB;
		}else if(hasIPC) {
			retValue = hasGerber || hasODB;
		}else if(hasODB) {
			retValue = hasGerber || hasIPC;
		}
		return retValue;
	}
	
	public int odbDirectoryCount() {
		int count = 0;
		if( directoryList != null ) {
			for (Directory directory : directoryList) {
				if( directory != null && "ODB".equalsIgnoreCase(directory.getType())) {
					count++;
				}
			}
		}
		return count;
	}
	
	public int ipcDirectoryCount() {
		int count = 0;
		if( directoryList != null ) {
			for (Directory directory : directoryList) {
				if( directory != null && "FILE_TYPE_IPC".equalsIgnoreCase(directory.getType())) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * @return the errorMessages
	 */
	public Set<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages the errorMessages to set
	 */
	public void setErrorMessages(Set<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public void addErrorMessage( String message) {
		if( this.errorMessages == null ) {
			this.errorMessages = new HashSet<String>();
		}
		this.errorMessages.add(message);
	}
	
	public void addWarningMessage( String message) {
		if( this.warningMessages == null ) {
			this.warningMessages = new HashSet<String>();
		}
		this.warningMessages.add(message);
	}
	
	public void addInfoMessage( String message) {
		if( this.infoMessages == null ) {
			this.infoMessages = new HashSet<String>();
		}
		this.infoMessages.add(message);
	}
	

	/**
	 * @return the warningMessages
	 */
	public Set<String> getWarningMessages() {
		return warningMessages;
	}

	/**
	 * @param warningMessages the warningMessages to set
	 */
	public void setWarningMessages(Set<String> warningMessages) {
		this.warningMessages = warningMessages;
	}

	/**
	 * @return the infoMessages
	 */
	public Set<String> getInfoMessages() {
		return infoMessages;
	}

	/**
	 * @param infoMessages the infoMessages to set
	 */
	public void setInfoMessages(Set<String> infoMessages) {
		this.infoMessages = infoMessages;
	}

	/**
	 * @return the directoryList
	 */
	public List<Directory> getDirectoryList() {
		return directoryList;
	}

	/**
	 * @param directoryList the directoryList to set
	 */
	public void setDirectoryList(List<Directory> directoryList) {
		this.directoryList = directoryList;
	}

	public void addDirectory( Directory dir) {
		if( this.directoryList == null ) {
			this.directoryList = new ArrayList<Directory>();
		}
		directoryList.add(dir);
	}
	
	public void setRemainingType( Map<String,String> ruleMap ) {
		if(this.directoryList != null) {
			this.directoryList.stream().forEach( dir -> {
				List<FileDetail> files = dir.getFiles();
				if(files != null) {
					files.stream().forEach( file -> {
						String type = file.getType();
						if( type == null || "File".equalsIgnoreCase(type) || "document".equalsIgnoreCase(type) 
								|| "".equalsIgnoreCase(type.trim()) || "gerber".equalsIgnoreCase(type) ) {
							String key = dir.getName() + File.separator + file.getName();
							if( ruleMap.containsKey(key)) {
								file.setType(ruleMap.get(key));
								if( StringUtils.isBlank(file.getType()) ) {
									file.setType("File");
								}
							}
						}
					});
				}
			});
		}
	}

}
