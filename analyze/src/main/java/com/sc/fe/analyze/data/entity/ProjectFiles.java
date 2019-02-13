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
    @Column(value = "type")
    private String type;
    @Column(value = "side")
    private String side;
    @Column(value = "layer_order")
    private int layerOrder;
    @Column(value = "start_name")
    private String startName;
    @Column(value = "end_name")
    private String endName;
    @Column(value = "copper_weight")
    private String copperWeight;
    @Column(value = "layer_name")
    private String layerName;
    @Column(value = "attributes")
    private Map<String, String> attributes;
    @Column(value = "create_date")
    private Date createDate;
    @Column(value = "modified_date")
    private Date modifiedDate;
    @Column(value = "errors")
    private Map<String, String> errors;

    /**
     * Gets the StartName
     *
     * @return
     */
    public String getStartName() {
        return startName;
    }

    /**
     * Sets the StartName into the startName variable.
     *
     * @param startName sets the startName
     */
    public void setStartName(String startName) {
        this.startName = startName;
    }

    /**
     * Gets the LayerOrder.
     *
     * @return
     */
    public int getLayerOrder() {
        return layerOrder;
    }

    /**
     * Sets the LayerOrder into the layerOrder variable.
     *
     * @param layerOrder sets the layerOrder
     */
    public void setLayerOrder(int layerOrder) {
        this.layerOrder = layerOrder;
    }

    /**
     * Gets the EndName.
     *
     * @return
     */
    public String getEndName() {
        return endName;
    }

    /**
     * Sets the EndName into the endName variable.
     *
     * @param endName sets the endName
     */
    public void setEndName(String endName) {
        this.endName = endName;
    }

    /**
     * Gets the LayerName.
     *
     * @return
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * Sets the LayerName into the layerName variable.
     *
     * @param layerName sets the layerName
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * Gets the CopperWeight.
     *
     * @return
     */
    public String getCopperWeight() {
        return copperWeight;
    }

    /**
     * Sets the CopperWeight into the copperWeight variable.
     *
     * @param copperWeight sets the copperWeight
     */
    public void setCopperWeight(String copperWeight) {
        this.copperWeight = copperWeight;
    }

    /**
     * Gets the CreateDate
     *
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the CreateDate into the createDate variable.
     *
     * @param createDate sets the createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the ModifiedDate.
     *
     * @return
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the ModifiedDate into the modifiedDate variable.
     *
     * @param modifiedDate sets the modifiedDate
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the Primary Key ProjectId
     *
     * @return
     */
    public String getProjectId() {
        return getKey().getProjectId();
    }

    /**
     * Sets the PrimaryKey ProjectId
     *
     * @param projectId sets the projectId
     */
    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);;
    }

    /**
     * Gets the PrimaryKey version.
     *
     * @return
     */
    public UUID getVersion() {
        return getKey().getVersion();
    }

    /**
     * Sets the PrimaryKey version.
     *
     * @param version sets the version.
     */
    public void setVersion(UUID version) {
        this.getKey().setVersion(version);
    }

    /**
     * Gets the Type.
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the Type into the type variable.
     *
     * @param type sets the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the name.
     *
     * @return
     */
    public String getName() {
        return getKey().getName();
    }

    /**
     * Sets the Name
     *
     * @param name takes the name and sets it
     */
    public void setName(String name) {
        this.getKey().setName(name);
    }

    /**
     * Gets the key, if key is null the initializes it.
     *
     * @return
     */
    public ProjectFilesPK getKey() {
        if (key == null) {
            key = new ProjectFilesPK();
        }
        return key;
    }

    /**
     * Sets the ProjectFiles PrimaryKey
     *
     * @param key sets the key
     */
    public void setKey(ProjectFilesPK key) {
        this.key = key;
    }

    /**
     * Sets the size into the size variable.
     *
     * @param size takes size and sets it.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the size.
     *
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the FileDate into the fileDate.
     *
     * @param fileDate takes the fileDate and sets it.
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     * Gets the FileDate
     *
     * @return
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * Sets the Format into the format.
     *
     * @param format takes format and sets it.
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Gets the Format.
     *
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the step into the step variable.
     *
     * @param step takes step and sets it.
     */
    public void setStep(String step) {
        this.step = step;
    }

    /**
     * Gets the step.
     *
     * @return
     */
    public String getStep() {
        return step;
    }

    /**
     * Sets the context into the context variable.
     *
     * @param context takes context and sets it
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Gets the context.
     *
     * @return
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the polarity into the polarity variable..
     *
     * @param polarity takes polarity and sets it
     */
    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    /**
     * Gets the Polarity.
     *
     * @return
     */
    public String getPolarity() {
        return polarity;
    }

    /**
     * Sets the side into the variable side.
     *
     * @param side takes side and sets it
     */
    public void setSide(String side) {
        this.side = side;
    }

    /**
     * Gets the Side.
     *
     * @return
     */
    public String getSide() {
        return side;
    }

    /**
     * Gets the Attribute.
     *
     * @return
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Sets the Attributes into the variable attributes.
     *
     * @param attributes
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Sets the Errors into the errors variable.
     *
     * @param errors takes errors and sets it
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * Gets the Errors.
     *
     * @return
     */
    public Map<String, String> getErrors() {
        return errors;
    }
}
