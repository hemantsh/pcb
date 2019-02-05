/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "project_files", description = "Table Structure project_files")
@Table(value = "project_files")
public class ProjectFiles {

    @PrimaryKey
    private ProjectFilesPK key;

    @Column(value = "size")
    private String size;
    @Column(value = "file_date")
    private Date fileDate;
    @Column(value = "format")
    private String format;
    @Column(value = "step")
    private String step;
    @Column(value = "context")
    private String context;
    @Column(value = "polarity")
    private String polarity;
    @Column(value = "side")
    private String side;
    @Column(value = "layer_order")
    private int layer_order;
    @Column(value = "start_name")
    private String start_name;
    @Column(value = "end_name")
    private String end_name;
    @Column(value = "copper_weight")
    private String copper_weight;
    @Column(value = "layer_name")
    private String layer_name;
    @Column(value = "attributes")
    private Map<String, String> attributes;
    @Column(value = "create_date")
    private Date create_date;
    @Column(value = "modified_date")
    private Date modified_date;
    @Column(value = "errors")
    private Map<String, String> errors;

    public String getProjectId() {
        return getKey().getProjectId();
    }

    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);;
    }

    public UUID getVersion() {
        return getKey().getVersion();
    }

    public void setVersion(UUID version) {
        this.getKey().setVersion(version);
    }

    public String getName() {
        return getKey().getName();
    }

    public void setName(String name) {
        this.getKey().setName(name);
    }

    public ProjectFilesPK getKey() {
        if (key == null) {
            key = new ProjectFilesPK();
        }
        return key;
    }

    public void setKey(ProjectFilesPK key) {
        this.key = key;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getSide() {
        return side;
    }

    public void setLayerOrder(int layerOrder) {
        this.layer_order = layerOrder;
    }

    public int getLayerOrder() {
        return layer_order;
    }

    public void setStartName(String startName) {
        this.start_name = startName;
    }

    public String getStartName() {
        return start_name;
    }

    public void setEndName(String endName) {
        this.end_name = endName;
    }

    public String getEndName() {
        return end_name;
    }

    public void setCopperWeight(String copperWeight) {
        this.copper_weight = copperWeight;
    }

    public String getCopperWeight() {
        return copper_weight;
    }

    public void setLayerName(String layerName) {
        this.layer_name = layerName;
    }

    public String getLayerName() {
        return layer_name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setCreateDate(Date createDate) {
        this.create_date = createDate;
    }

    public Date getCreateDate() {
        return create_date;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modified_date = modifiedDate;
    }

    public Date getModifiedDate() {
        return modified_date;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
