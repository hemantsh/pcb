package com.sc.fe.analyze.data.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.Services;

/**
 * 
 * @author Hemant
 */
@Repository
public interface ServicesRepo extends CassandraRepository<Services, Integer> {

}
