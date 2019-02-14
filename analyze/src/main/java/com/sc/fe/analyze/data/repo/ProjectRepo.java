/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectPK;

/**
 *
 * @author Hemant
 */
@Repository
public interface ProjectRepo extends CassandraRepository<Project, ProjectPK> {

    /**
     * Extracts data by the projectId from the database.
     *
     * @param projectId takes projectId
     * @return
     */
    List<Project> findByKeyProjectId(final String projectId);

    /**
     * Extracts data by customerId from database.
     *
     * @param customerId takes customerid
     * @return
     */
    List<Project> findByCustomerId(final String customerId);

    /**
     * Extracts data by the CustomerEmail from database.
     *
     * @param customerEmail takes customerEmail
     * @return
     */
    List<Project> findByCustomerEmail(final String customerEmail);

    /**
     * Extracts data by zipfileName from database.
     *
     * @param zipFileName takes zipfileName
     * @return
     */
    List<Project> findByZipFileName(final String zipFileName);

}
