/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.repo;

import com.sc.fe.analyze.data.entity.DifferenceReport;
import com.sc.fe.analyze.data.entity.DifferenceReportPK;
import com.sc.fe.analyze.to.ProjectDetails;

import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hemant
 */
@Repository
public interface DifferenceReportRepo extends CassandraRepository<DifferenceReport, DifferenceReportPK> {

    List<ProjectDetails> findByKeyProjectId(final String projectId);
}
