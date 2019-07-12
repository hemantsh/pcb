package com.sc.fe.analyze.data.repo;

import com.sc.fe.analyze.data.entity.ServiceFiletypes;
import com.sc.fe.analyze.data.entity.ServiceFiletypesPK;
import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hemant
 */
@Repository
public interface ServiceFiletypesRepo extends CassandraRepository<ServiceFiletypes, ServiceFiletypesPK> {

    List<ServiceFiletypes> findByKeyServiceid(final int serviceid);

    ServiceFiletypes findByKeyFiletype(final String filetype);
}
