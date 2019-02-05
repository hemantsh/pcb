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

@ApiModel(value = "Report", description = "Table Structure Report")
@Table(value = "project")
public class Project {

    @PrimaryKey
    private ProjectPK key;

    @Column(value = "customer_name")
    private String customerName;
    @Column(value = "customer_id")
    private String customerId;
    @Column(value = "customer_email")
    private String customerEmail;
    @Column(value = "service_type")
    private String serviceType;
    @Column(value = "layer_count")
    private int layerCount;
    @Column(value = "pcb_class")
    private String pcbClass;
    @Column(value = "board_type")
    private String boardType;
    @Column(value = "itar")
    private String itar;
    @Column(value = "turntime_quantity")
    private Map<Integer, Integer> turntimeQuantity;
    @Column(value = "design_specification")
    private String designSpecification;
    @Column(value = "zipfile_name")
    private String zipfileName;
    @Column(value = "zipfile_size")
    private String zipfileSize;
    @Column(value = "create_date")
    private Date createDate;
    @Column(value = "modified_date")
    private Date modifiedDate;
    @Column(value = "errors")
    private Map<String, String> errors;

    public ProjectPK getKey() {
        if (key == null) {
            key = new ProjectPK();
        }
        return key;
    }

    public void setKey(ProjectPK key) {
        this.key = key;
    }

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEamil() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String servicetype) {
        this.serviceType = servicetype;
    }

    public int getLayerCount() {
        return layerCount;
    }

    public void setLayerCount(int layerCount) {
        this.layerCount = layerCount;
    }

    public String getPcbClass() {
        return pcbClass;
    }

    public void setPcbClass(String pcbClass) {
        this.pcbClass = pcbClass;
    }

    public void getBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getItar() {
        return itar;
    }

    public Map<Integer, Integer> getTurnTimeQuantity() {
        return turntimeQuantity;
    }

    public void setTurnTimeQuantity(Map<Integer, Integer> turnTimeQuantity) {
        this.turntimeQuantity = turnTimeQuantity;
    }

    public String getDesignSpecification() {
        return designSpecification;
    }

    public void setDesignSpecification(String desingSpec) {
        this.designSpecification = desingSpec;
    }

    public String getZipFileName() {
        return zipfileName;
    }

    public void setZipFileName(String zipFileName) {
        this.zipfileName = zipFileName;
    }

    public String getZipFileSize() {
        return zipfileSize;
    }

    public void setZipFileSize(String zipFileSize) {
        this.zipfileSize = zipFileSize;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public Map<Integer, Integer> getTurntimeQuantity() {
        return turntimeQuantity;
    }

    public void setTurntimeQuantity(Map<Integer, Integer> turntimeQuantity) {
        this.turntimeQuantity = turntimeQuantity;
    }

    public String getZipfileName() {
        return zipfileName;
    }

    public void setZipfileName(String zipfileName) {
        this.zipfileName = zipfileName;
    }

    public String getZipfileSize() {
        return zipfileSize;
    }

    public void setZipfileSize(String zipfileSize) {
        this.zipfileSize = zipfileSize;
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

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> error) {
        this.errors = error;
    }

    public void setModifiedDate(String mMddyyyy_HHmmss) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
