package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.ExtensionFilePK;
import com.sc.fe.analyze.data.entity.ExtensionFileType;

@Repository
public interface ExtensionFileRepo extends CassandraRepository<ExtensionFileType, ExtensionFilePK> {

    /**
     * Used to Extract the data by the extensionId from the database.
     *
     * @param extensionId takes extensionId which can be passed to database.
     * @return the list of extension
     */
    List<ExtensionFileType> findByKeyExtensionId(final int extensionId);

    /**
     * Used to Extract the data by the filetypeId from the database.
     *
     * @param filetypeId take filetypeId which can be passed to database.
     * @return the list of ExtensionFileType
     */
    List<ExtensionFileType> findByKeyFiletypeId(final int filetypeId);
}
