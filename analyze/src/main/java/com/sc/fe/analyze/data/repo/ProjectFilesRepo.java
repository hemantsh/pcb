package com.sc.fe.analyze.data.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.ProjectFiles;
import com.sc.fe.analyze.data.entity.ProjectFilesPK;

/**
 *
 * @author Hemant
 */
@Repository
public interface ProjectFilesRepo extends CassandraRepository< ProjectFiles, ProjectFilesPK> {

    /**
     * Used to extract data by projectId
     *
     * @param projectId takes projectId
     * @return the list of projectFiles
     */
    List<ProjectFiles> findByKeyProjectId(final String projectId);

    /**
     * Used to find data by combined primaryKey ProjectId and Version.
     *
     * @param projectId takes projectId
     * @param version takes version
     * @return the list of ProjectFiles
     */
    List<ProjectFiles> findByKeyProjectIdAndKeyVersion(final String projectId, final UUID version);
}
