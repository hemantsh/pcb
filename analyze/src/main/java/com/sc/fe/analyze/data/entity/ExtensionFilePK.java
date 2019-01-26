package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@ApiModel(value="ExtensionFilePK",description="Combined Primary Key of ExtensionFiles")
@PrimaryKeyClass
public class ExtensionFilePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5975930711338764582L;
        
	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name="extension_id")
        @ApiModelProperty("First Primary Key")
	private int extensionId;
	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name="filetype_id")
        @ApiModelProperty("Second Primary Key")
	private int filetypeId;
	
	public int getExtensionId() {
		return extensionId;
	}
	public void setExtensionId(int extensionId) {
		this.extensionId = extensionId;
	}
	public int getFiletypeId() {
		return filetypeId;
	}
	public void setFiletypeId(int filetypeId) {
		this.filetypeId = filetypeId;
	}
}
