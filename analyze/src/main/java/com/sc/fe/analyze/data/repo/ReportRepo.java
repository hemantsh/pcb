package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.data.entity.ReportPK;

@Repository
public interface ReportRepo extends CassandraRepository<Report, ReportPK> {
	
	List<Report> findByKeyProjectId(final String projectId);
}
