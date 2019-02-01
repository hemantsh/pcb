/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.repo;


import com.sc.fe.analyze.data.entity.ProjectFiles;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.ProjectFilesPK;
import java.util.List;
/**
 *
 * @author Hemant
 */
@Repository
public interface ProjectFilesRepo extends CassandraRepository< ProjectFiles,ProjectFilesPK>{
    List<ProjectFiles> findByKeyProjectId(final String projectId);
}
