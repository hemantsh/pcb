package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.Report;
import com.sc.fe.analyze.data.entity.ReportPK;

/**
 * 
 * @author Hemant
 */
@Repository
public interface ReportRepo extends CassandraRepository<Report, ReportPK> {

    /**
     * Extracts data by the projectId from the database.
     *
     * @param projectId Takes the projectId as an argument.
     * @return the list of report
     */
    List<Report> findByKeyProjectId(final String projectId);
}
