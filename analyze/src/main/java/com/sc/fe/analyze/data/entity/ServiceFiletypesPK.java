package com.sc.fe.analyze.data.entity;

import java.io.Serializable;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

/**
 *
 * @author Hemant
 */
@PrimaryKeyClass
public class ServiceFiletypesPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = "service_id")
    private int serviceid;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name = "file_type")
    private String filetype;

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String file_type) {
        this.filetype = file_type.toLowerCase();
    }

}
