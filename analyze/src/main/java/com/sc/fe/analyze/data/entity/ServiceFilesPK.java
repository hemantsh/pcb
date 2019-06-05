package com.sc.fe.analyze.data.entity;

import java.io.Serializable;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
//Todo This class is not going to be used anymore.....................................
@ApiModel(value = "ServiceFiles", description = "Combined Primary Key Structure of ServiceFiles")
@PrimaryKeyClass
public class ServiceFilesPK implements Serializable {

    private static final long serialVersionUID = 7133581623671470779L;

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED, name = "service_id")
    @ApiModelProperty("First Primary Key")
    private int serviceId;
    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED, name = "filetype_id")
    @ApiModelProperty("Second Primary Key")
    private int filetypeId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.valueOf(serviceId).hashCode();
        result = prime * result + Integer.valueOf(filetypeId).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        ServiceFilesPK other = (ServiceFilesPK) obj;
        if (other.filetypeId != filetypeId) {
            return false;
        }
        if (other.serviceId != serviceId) {
            return false;
        }
        return true;
    }

    /**
     * Gets the serviceId.
     *
     * @return the serviceId
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Sets the serviceId into the variable serviceId.
     *
     * @param serviceId takes serviceId and sets it
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Gets the FiletypeId.
     *
     * @return the fieType id
     */
    public int getFiletypeId() {
        return filetypeId;
    }

    /**
     * Sets the fileTypeId into the variable filetypeId
     *
     * @param filetypeId takes filetypeId and sets it
     */
    public void setFiletypeId(int filetypeId) {
        this.filetypeId = filetypeId;
    }
}
