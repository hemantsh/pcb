package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@ApiModel(value="Services",description="Table Structure of Services")
@Table(value="services")
public class Services {
	
	@PrimaryKey
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
