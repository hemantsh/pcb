package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@ApiModel(value = "Services", description = "Table Structure of Services")
@Table(value = "services")
public class Services {

    @PrimaryKey
    private int id;
    private String name;

    /**
     * Gets the Id.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id into the variable id.
     *
     * @param id takes id and sets it
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name into the variable name
     *
     * @param name takes name and sets it
     */
    public void setName(String name) {
        this.name = name;
    }

}
