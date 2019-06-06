package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;
//Todo This class is not going to be used anymore.....................................
@ApiModel(value = "FileType", description = "Table Structure of Filetypes")
@Table(value = "file_types")
public class FileTypes {

    @PrimaryKey
    private int id;
    private String type;

    /**
     * Gets the Id.
     *
     * @return the Id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Id in the id instance variable.
     *
     * @param id Sets the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type into the type variable.
     *
     * @param type Sets the type.
     */
    public void setType(String type) {
        this.type = type;
    }
}
