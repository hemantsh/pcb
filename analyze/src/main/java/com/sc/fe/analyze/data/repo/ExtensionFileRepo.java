package com.sc.fe.analyze.data.repo;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sc.fe.analyze.data.entity.ExtensionFilePK;
import com.sc.fe.analyze.data.entity.ExtensionFileType;
import org.springframework.data.cassandra.repository.Query;

@Repository
public interface ExtensionFileRepo extends CassandraRepository <ExtensionFileType, ExtensionFilePK > {
	List<ExtensionFileType> findByKeyExtensionId(final int extensionId);
        @Query("select * from extension_file where filetype_id=?0 ALLOW FILTERING;")
        List<ExtensionFileType> findByKeyFiletypeId(final int filetypeId);
}
