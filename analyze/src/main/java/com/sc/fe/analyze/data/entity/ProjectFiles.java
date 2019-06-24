package com.sc.fe.analyze.data.entity;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "project_files", description = "Table Structure project_files")
@Table(value = "project_files")
public class ProjectFiles implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public int getLayerOrder() {
        return layerOrder;
    }

    public void setLayerOrder(int layerOrder) {
        this.layerOrder = layerOrder;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getCopperWeight() {
        return copperWeight;
    }

    public void setCopperWeight(String copperWeight) {
        this.copperWeight = copperWeight;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getProjectId() {
        return getKey().getProjectId();
    }

    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);
    }

    public UUID getVersion() {
        return getKey().getVersion();
    }

    public void setVersion(UUID version) {
        this.getKey().setVersion(version);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
