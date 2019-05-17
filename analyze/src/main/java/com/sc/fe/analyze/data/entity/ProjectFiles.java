/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sc.fe.analyze.data.entity;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;

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
    @Column(value = "selected")
    private boolean selected;
    @Column(value = "status")
    private String status;

    /**
     * Gets the status Value
     *
     * @return the status value
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the Status value
     *
     * @param status Sets the status value.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the selected Value
     *
     * @return the selected value
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the Selected value
     *
     * @param selected Sets the selected value.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Gets the StartName
     *
     * @return the startName
     */
    public String getStartName() {
        return startName;
    }

    /**
     * Sets the StartName into the startName instance variable.
     *
     * @param startName Sets the startName
     */
    public void setStartName(String startName) {
        this.startName = startName;
    }

    /**
     * Gets the LayerOrder.
     *
     * @return the layerOrder
     */
    public int getLayerOrder() {
        return layerOrder;
    }

    /**
     * Sets the LayerOrder into the layerOrder instance variable.
     *
     * @param layerOrder Sets the layerOrder
     */
    public void setLayerOrder(int layerOrder) {
        this.layerOrder = layerOrder;
    }

    /**
     * Gets the EndName.
     *
     * @return the endName
     */
    public String getEndName() {
        return endName;
    }

    /**
     * Sets the EndName into the endName variable.
     *
     * @param endName Sets the endName
     */
    public void setEndName(String endName) {
        this.endName = endName;
    }

    /**
     * Gets the LayerName.
     *
     * @return the layerName
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * Sets the LayerName into the layerName instance variable.
     *
     * @param layerName Sets the layerName
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     * Gets the CopperWeight.
     *
     * @return the copperWeight
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
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the CreateDate into the createDate variable.
     *
     * @param createDate Sets the createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the ModifiedDate.
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the ModifiedDate into the modifiedDate variable.
     *
     * @param modifiedDate Sets the modifiedDate
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the Primary Key ProjectId
     *
     * @return the projectId
     */
    public String getProjectId() {
        return getKey().getProjectId();
    }

    /**
     * Sets the PrimaryKey ProjectId
     *
     * @param projectId Sets the projectId
     */
    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);
    }

    /**
     * Gets the PrimaryKey version.
     *
     * @return the version.
     */
    public UUID getVersion() {
        return getKey().getVersion();
    }

    /**
     * Sets the PrimaryKey version.
     *
     * @param version Sets the version.
     */
    public void setVersion(UUID version) {
        this.getKey().setVersion(version);
    }

    /**
     * Gets the Type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the Type into the type variable.
     *
     * @param type Sets the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the name.
     *
     * @return the name
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
     * Gets the Primary Key, if null then initialize the new PrimaryKey.
     *
     * @return the PrimaryKey values.
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
     * @param key Sets the key
     */
    public void setKey(ProjectFilesPK key) {
        this.key = key;
    }

    /**
     * Sets the size into the size variable.
     *
     * @param size Takes size and sets it.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the size.
     *
     * @return the size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the FileDate into the fileDate.
     *
     * @param fileDate Takes the fileDate and sets it.
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     * Gets the FileDate
     *
     * @return the fileDate
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * Sets the Format value into the format variable.
     *
     * @param format Takes the format value and sets it.
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Gets the Format.
     *
     * @return the format value
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the step value into the step variable.
     *
     * @param step Takes step value and sets it.
     */
    public void setStep(String step) {
        this.step = step;
    }

    /**
     * Gets the step value.
     *
     * @return the Step value.
     */
    public String getStep() {
        return step;
    }

    /**
     * Sets the context value into the context variable.
     *
     * @param context Takes context value and sets it
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Gets the context.
     *
     * @return the context value.
     */
    public String getContext() {
        return context;
    }

    /**
     * Sets the polarity value into the polarity variable..
     *
     * @param polarity Takes polarity value and sets it
     */
    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    /**
     * Gets the Polarity value.
     *
     * @return the polarity value
     */
    public String getPolarity() {
        return polarity;
    }

    /**
     * Sets the side value into the variable side.
     *
     * @param side Takes the side value and sets it
     */
    public void setSide(String side) {
        this.side = side;
    }

    /**
     * Gets the Side value.
     *
     * @return the side value
     */
    public String getSide() {
        return side;
    }

    /**
     * Gets the Attribute.
     *
     * @return the attributes in key-value pair
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Sets the Attributes into the variable attributes.
     *
     * @param attributes Sets the attribute in (key,value) pair.
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Sets the Errors into the errors variable.
     *
     * @param errors Takes errors and sets it
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * Gets the Errors.
     *
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }
}
