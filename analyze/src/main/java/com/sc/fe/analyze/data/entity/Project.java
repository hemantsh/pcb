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
    private boolean itar;
    @Column(value = "turntime_quantity")
    private Map<Integer, Integer> turntimeQuantity;
    @Column(value = "design_specification")
    private String designSpecification;
    @Column(value = "zipfile_name")
    private String zipFileName;
    @Column(value = "zipfile_size")
    private String zipfileSize;
    @Column(value = "create_date")
    private Date createDate;
    @Column(value = "modified_date")
    private Date modifiedDate;
    @Column(value = "errors")
    private Map<String, String> errors;
    @Column(value = "nofly")
    private boolean nofly;

    /**
     * Gets the nofly value.
     *
     * @return the nofly value.
     */
    public boolean getNofly() {
        return nofly;
    }

    /**
     * Sets the nofly value.
     *
     * @param nofly Sets the nofly value.
     */
    public void setNofly(boolean nofly) {
        this.nofly = nofly;
    }

    /**
     * Gets the Key, if null then Initializes the ProjectPrimaryKey.
     *
     * @return the key.
     */
    public ProjectPK getKey() {
        if (key == null) {
            key = new ProjectPK();
        }
        return key;
    }

    /**
     * Sets the Primary Key.
     *
     * @param key Sets the key.
     */
    public void setKey(ProjectPK key) {
        this.key = key;
    }

    /**
     * Gets the ProjectId.
     *
     * @return the projectId.
     */
    public String getProjectId() {
        return getKey().getProjectId();
    }

    /**
     * Sets the Composite PrimaryKey ProjectId.
     *
     * @param projectId Sets the projectId.
     */
    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);
    }

    /**
     * Gets the Composite PrimaryKey version.
     *
     * @return the version.
     */
    public UUID getVersion() {
        return getKey().getVersion();
    }

    /**
     * Sets the Composite PrimaryKey version.
     *
     * @param version Sets the version.
     */
    public void setVersion(UUID version) {
        this.getKey().setVersion(version);
    }

    /**
     * Gets the CustomerName.
     *
     * @return the CustomerName.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the CustomerName into the CustomerName variable.
     *
     * @param customerName sets the customerName.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the CustomerId.
     *
     * @return the CustomerId.
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the CustomerId into the customerId instance variable.
     *
     * @param customerId Sets the customerId.
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the CustomerEmail.
     *
     * @return the CustomerEmail.
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the CustomerEmail into the customerEmail instance variable.
     *
     * @param customerEmail Sets the customerEmail.
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Gets the ServiceType
     *
     * @return the ServiceType.
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the serviceType into the serviceType instance variable.
     *
     * @param servicetype Sets the serviceType.
     */
    public void setServiceType(String servicetype) {
        this.serviceType = servicetype;
    }

    /**
     * Gets the LayerCount.
     *
     * @return the layerCount
     */
    public int getLayerCount() {
        return layerCount;
    }

    /**
     * Sets the LayerCount into the layerCount instance variable.
     *
     * @param layerCount Sets the layerCount.
     */
    public void setLayerCount(int layerCount) {
        this.layerCount = layerCount;
    }

    /**
     * Gets the PCBClass.
     *
     * @return the PCBClass.
     */
    public String getPcbClass() {
        return pcbClass;
    }

    /**
     * Sets the PCBClass into pcbClass variable.
     *
     * @param pcbClass Sets the pcbClass.
     */
    public void setPcbClass(String pcbClass) {
        this.pcbClass = pcbClass;
    }

    /**
     * Gets the Itar.
     *
     * @return the Itar
     */
    public boolean getItar() {
        return itar;
    }

    /**
     * Sets the Itar into the itar variable.
     *
     * @param itar Sets the itar.
     */
    public void setItar(boolean itar) {
        this.itar = itar;
    }

    /**
     * Gets the DesignSpecification.
     *
     * @return the designSpecification
     */
    public String getDesignSpecification() {
        return designSpecification;
    }

    /**
     * Sets the DesginSpecification into the designSpecification Variable.
     *
     * @param desingSpec Sets the designSpecification.
     */
    public void setDesignSpecification(String desingSpec) {
        this.designSpecification = desingSpec;
    }

    /**
     * Gets the BoardType.
     *
     * @return the boardType.
     */
    public String getBoardType() {
        return boardType;
    }

    /**
     * Sets the BoardType into the boardType variable.
     *
     * @param boardType Sets the BoardType.
     */
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    /**
     * Gets the TurnTimeQuantity.
     *
     * @return the turnTimeQuantity
     */
    public Map<Integer, Integer> getTurntimeQuantity() {
        return turntimeQuantity;
    }

    /**
     * Sets the TurnTimeQuanity into the turntimeQuanity instance variable.
     *
     * @param turntimeQuantity Sets the turntimeQuantity
     */
    public void setTurntimeQuantity(Map<Integer, Integer> turntimeQuantity) {
        this.turntimeQuantity = turntimeQuantity;
    }

    /**
     * Gets the ZipFileName.
     *
     * @return the zipFileName.
     */
    public String getZipfileName() {
        return zipFileName;
    }

    /**
     * Sets the ZipfileName into the zipFileName instance variable.
     *
     * @param zipfileName Sets the ZipFileName.
     */
    public void setZipfileName(String zipfileName) {
        this.zipFileName = zipfileName;
    }

    /**
     * Gets the ZipFileSize.
     *
     * @return the zipFileSize.
     */
    public String getZipfileSize() {
        return zipfileSize;
    }

    /**
     * Sets the ZipFileSize into the zipfileSize instance variable.
     *
     * @param zipfileSize Sets the zipFileSize.
     */
    public void setZipfileSize(String zipfileSize) {
        this.zipfileSize = zipfileSize;
    }

    /**
     * Gets the CreateDate.
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
     * Gets the Modified Date.
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the Modified Date into the modifiedDate variable.
     *
     * @param modifiedDate Sets the modifiedDate
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the Errors.
     *
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Sets the Errors into the errors variable.
     *
     * @param error Sets the errors
     */
    public void setErrors(Map<String, String> error) {
        this.errors = error;
    }

    public void setModifiedDate(String mMddyyyy_HHmmss) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
