/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.repo;

import com.sc.fe.analyze.data.entity.FiletypeExtensions;
import com.sc.fe.analyze.data.entity.FiletypeExtensionsPK;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface FiletypeExtensionsRepo extends CassandraRepository<FiletypeExtensions, FiletypeExtensionsPK> {

    List<FiletypeExtensions> findByExtensions(final String extension);

//    void deleteByKeyId(final UUID id);
}
