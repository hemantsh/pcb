package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@ApiModel(value = "FileType", description = "Table Structure of Filetypes")
@Table(value = "file_types")
public class FileTypes {

    @PrimaryKey
    private int id;
    private String type;

    /**
     * Gets the Id.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the Id into the id variable.
     *
     * @param id sets the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the type.
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type into the type variable.
     *
     * @param type sets the type.
     */
    public void setType(String type) {
        this.type = type;
    }

}
