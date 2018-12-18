package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value="extension_file")
public class ExtensionFileType {

	@PrimaryKey
	private ExtensionFilePK key;
	
	private String extension;
 	private String file;
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
	public int getExtensionId() {
		return key.getExtensionId();
	}
	public void setExtensionId(int extensionId) {
		this.key.setExtensionId(extensionId);
	}
	public int getFiletypeId() {
		return key.getExtensionId();
	}
	public void setFiletypeId(int filetypeId) {
		this.key.setExtensionId(filetypeId);
	}
	
}
