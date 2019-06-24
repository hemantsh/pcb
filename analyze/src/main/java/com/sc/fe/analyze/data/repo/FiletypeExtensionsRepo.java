package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.FiletypeExtensionsPK;

/**
 *
 * @author Hemant
 */
@Repository
public interface FiletypeExtensionsRepo extends CassandraRepository<FiletypeExtensions, FiletypeExtensionsPK> {

    List<FiletypeExtensions> findByExtensions(final String extension);

}
