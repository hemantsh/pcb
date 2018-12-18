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
		return getKey().getExtensionId();
	}
	public void setExtensionId(int extensionId) {
		this.getKey().setExtensionId(extensionId);
	}
	public int getFiletypeId() {
		return getKey().getExtensionId();
	}
	public void setFiletypeId(int filetypeId) {
		this.getKey().setExtensionId(filetypeId);
	}
	public ExtensionFilePK getKey() {
		if(key == null) {
			key = new ExtensionFilePK();
		}
		return key;
	}
	public void setKey(ExtensionFilePK key) {
		this.key = key;
	}
	
}
