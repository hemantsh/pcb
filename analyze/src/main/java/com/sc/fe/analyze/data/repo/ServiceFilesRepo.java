package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.ServiceFiles;
import com.sc.fe.analyze.data.entity.ServiceFilesPK;

//@Repository
//public interface ServiceFilesRepo extends CassandraRepository<ServiceFiles, ServiceFilesPK> {
//
//    /**
//     * Extracts data by serviceId from the database.
//     *
//     * @param serviceId Takes the serviceId
//     * @return the list of Services
//     */
//    List<ServiceFiles> findByKeyServiceId(final int serviceId);
//}
