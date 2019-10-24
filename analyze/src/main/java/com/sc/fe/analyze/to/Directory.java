package com.sc.fe.analyze.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Directory", description = "Structure of Directory")
@JsonInclude(Include.NON_NULL)
public class Directory implements Serializable {
	
	private static final long serialVersionUID = -1150032561607661086L;

	public Directory() {
		super();
	}
	
	private String name;
	private String type;
	private List<FileDetail> files;
	
	
	@JsonIgnore
	public FileDetail getFileDetailInstance() {
		return new FileDetail();
	}
	
	/**
	 * @return the fileDetails
	 */
	public List<FileDetail> getFiles() {
		return files;
	}
	/**
	 * @param fileDetails the fileDetails to set
	 */
	public void setFiles(List<FileDetail> fileDetails) {
		this.files = fileDetails;
	}
	
	
	public void addFileDetail( FileDetail file ) {
		if( files == null ) {
			files = new ArrayList<FileDetail>();
		}
		files.add(file);
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	

	@JsonInclude(Include.NON_NULL)
	public class FileDetail {
		public FileDetail() {
			super();
		}
		
		private String name;
		private String type;
		private String dirName;
		
		@JsonIgnore
		public boolean isFile() {
			return ! StringUtils.isNullOrEmpty(name);
		}
		
		/**
		 * @return the fileType
		 */
		public String getType() {
			return type;
		}
		/**
		 * @param fileType the fileType to set
		 */
		public void setType(String fileType) {
			this.type = fileType;
		}
		/**
		 * @return the fileName
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param fileName the fileName to set
		 */
		public void setName(String fileName) {
			this.name = fileName;
		}
		
		/**
		 * @return the dirName
		 */
		public String getDirName() {
			return dirName;
		}
		/**
		 * @param dirName the dirName to set
		 */
		public void setDirName(String dirName) {
			this.dirName = dirName;
		}
	}

}