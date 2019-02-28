package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Extensions", description = "Table Structure of Extensions")
@Table(value = "extensions")
public class Extensions {

    @PrimaryKey
    private int id;
    private String name;

    /**
     * Gets the Id
     *
     * @return the Id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id into the id variable.
     *
     * @param id Sets the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name in the instance variable name.
     *
     * @param name Sets the name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
