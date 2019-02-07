/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.repo;

import com.sc.fe.analyze.data.entity.Project;
import com.sc.fe.analyze.data.entity.ProjectPK;
import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hemant
 */
@Repository
public interface ProjectRepo extends CassandraRepository<Project, ProjectPK>{
    
    List<Project> findByKeyProjectId(final String projectId); 
    List<Project> findByCustomerId(final String customerId);    
    List<Project> findByCustomerEmail(final String customerEmail);        
    List<Project> findByZipFileName(final String zipFileName);    
    
}
    