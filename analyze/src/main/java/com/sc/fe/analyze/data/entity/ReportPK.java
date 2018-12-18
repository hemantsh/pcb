package com.sc.fe.analyze.data.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass 
public class ReportPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5395574627764636399L;
	
	@PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name="project_id")
	private String projectId;
	@PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name="version", ordering=Ordering.DESCENDING)
	private int version;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}