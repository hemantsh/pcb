package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@ApiModel(value="FileType",description="Table Structure of Filetypes")
@Table(value="file_types")
public class FileTypes {
	
	@PrimaryKey
	private int id;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
