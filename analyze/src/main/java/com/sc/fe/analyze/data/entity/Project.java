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

	//TODO: add nofly column 
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
    private String zipFileName;
    @Column(value = "zipfile_size")
    private String zipfileSize;
    @Column(value = "create_date")
    private Date createDate;
    @Column(value = "modified_date")
    private Date modifiedDate;
    @Column(value = "errors")
    private Map<String, String> errors;

    /**
     * Gets the Key, if null then Initializes the ProjectPrimaryKey.
     *
     * @return
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
     * @param key sets the key.
     */
    public void setKey(ProjectPK key) {
        this.key = key;
    }

    /**
     * Gets the ProjectId.
     *
     * @return
     */
    public String getProjectId() {
        return getKey().getProjectId();
    }

    /**
     * Sets the Composite PrimaryKey ProjectId.
     *
     * @param projectId sets the projectId.
     */
    public void setProjectId(String projectId) {
        this.getKey().setProjectId(projectId);;
    }

    /**
     * Gets the Composite PrimaryKey version.
     *
     * @return
     */
    public UUID getVersion() {
        return getKey().getVersion();
    }

    /**
     * Sets the Composite PrimaryKey version.
     *
     * @param version sets the version.
     */
    public void setVersion(UUID version) {
        this.getKey().setVersion(version);
    }

    /**
     * Gets the CustomerName.
     *
     * @return
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
     * @return
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the CustomerId into the customerId variable.
     *
     * @param customerId sets the customerId.
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the CustomerEmail.
     *
     * @return
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the CustomerEmail into the customerEmail.
     *
     * @param customerEmail sets the customerEmail.
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    /**
     * Gets the ServiceType
     *
     * @return
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the serviceType into the serviceType variable.
     *
     * @param servicetype sets the serviceType.
     */
    public void setServiceType(String servicetype) {
        this.serviceType = servicetype;
    }

    /**
     * Gets the LayerCount.
     *
     * @return
     */
    public int getLayerCount() {
        return layerCount;
    }

    /**
     * Sets the LayerCount into the layerCount.
     *
     * @param layerCount sets the layerCount.
     */
    public void setLayerCount(int layerCount) {
        this.layerCount = layerCount;
    }

    /**
     * Gets the PCBClass.
     *
     * @return
     */
    public String getPcbClass() {
        return pcbClass;
    }

    /**
     * Sets the PCBClass into pcbClass variable.
     *
     * @param pcbClass sets the pcbClass.
     */
    public void setPcbClass(String pcbClass) {
        this.pcbClass = pcbClass;
    }

    /**
     * Gets the Itar.
     *
     * @return
     */
    public String getItar() {
        return itar;
    }

    /**
     * Sets the Itar into the itar variable.
     *
     * @param itar sets the itar.
     */
    public void setItar(String itar) {
        this.itar = itar;
    }

    /**
     * Gets the DesignSpecification.
     *
     * @return
     */
    public String getDesignSpecification() {
        return designSpecification;
    }

    /**
     * Sets the DesginSpecification into the designSpecification Variable.
     *
     * @param desingSpec sets the designSpecification.
     */
    public void setDesignSpecification(String desingSpec) {
        this.designSpecification = desingSpec;
    }

    /**
     * Gets the BoardType.
     *
     * @return
     */
    public String getBoardType() {
        return boardType;
    }

    /**
     * Sets the BoardType into boardType variable.
     *
     * @param boardType
     */
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    /**
     * Gets the TurnTimeQuantity.
     *
     * @return
     */
    public Map<Integer, Integer> getTurntimeQuantity() {
        return turntimeQuantity;
    }

    /**
     * Sets the TurnTimeQuanity into the turntimeQuanity variable.
     *
     * @param turntimeQuantity sets the turntimeQuantity
     */
    public void setTurntimeQuantity(Map<Integer, Integer> turntimeQuantity) {
        this.turntimeQuantity = turntimeQuantity;
    }

    /**
     * Gets the ZipFileName.
     *
     * @return
     */
    public String getZipfileName() {
        return zipFileName;
    }

    /**
     * Sets the ZipfileName into the zipFileName variable.
     *
     * @param zipfileName
     */
    public void setZipfileName(String zipfileName) {
        this.zipFileName = zipfileName;
    }

    /**
     * Gets the ZipFileSize.
     *
     * @return
     */
    public String getZipfileSize() {
        return zipfileSize;
    }

    /**
     * Sets the ZipFileSize into the zipfileSize variable.
     *
     * @param zipfileSize
     */
    public void setZipfileSize(String zipfileSize) {
        this.zipfileSize = zipfileSize;
    }

    /**
     * Gets the CreateDate.
     *
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets the CreateDate into the createDate variable.
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets the Modified Date.
     *
     * @return
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the Modified Date into the modifiedDate variable.
     *
     * @param modifiedDate
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the Errors.
     *
     * @return
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Sets the Errors into the errors variable.
     *
     * @param error
     */
    public void setErrors(Map<String, String> error) {
        this.errors = error;
    }

    public void setModifiedDate(String mMddyyyy_HHmmss) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
