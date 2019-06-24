package com.sc.fe.analyze.data.repo;

import com.sc.fe.analyze.data.entity.DifferenceReport;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hemant
 */
@Repository
public interface DifferenceReportRepo extends CassandraRepository<DifferenceReport, String> {
}
