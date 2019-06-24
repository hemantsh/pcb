package com.sc.fe.analyze.data.entity;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel(value = "Project", description = "Table Structure Report")
@Table(value = "project")
public class Project implements Serializable {

    private static final long serialVersionUID = -2878322995481840187L;

    @PrimaryKey
    private ProjectPK key;

    @Column(value = "part_number")
    private String partNumber;
    @Column(value = "rnumber")
    private String rNumber;
    @Column(value = "setId")
    private String setId;
    @Column(value = "company")
    private String company;
    @Column(value = "part_rev")
    private String partRev;
    @Column(value = "assembly_spec")
    private String assemblySpec;
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
    @Column(value = "assembly_turntime_quantity")
    private String assemblyTurntimeQuantity;
    @Column(value = "fabrication_turntime_quantity")
    private String fabricationTurntimeQuantity;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPartRev() {
        return partRev;
    }

    public void setPartRev(String partRev) {
        this.partRev = partRev;
    }

    public String getAssemblySpec() {
        return assemblySpec;
    }

    public void setAssemblySpec(String assemblySpec) {
        this.assemblySpec = assemblySpec;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getrNumber() {
        return rNumber;
    }

    public void setrNumber(String rNumber) {
        this.rNumber = rNumber;
    }

    public String getAssemblyTurntimeQuantity() {
        return assemblyTurntimeQuantity;
    }

    public void setAssemblyTurntimeQuantity(String assemblyTurntimeQuantity) {
        this.assemblyTurntimeQuantity = assemblyTurntimeQuantity;
    }

    public String getFabricationTurntimeQuantity() {
        return fabricationTurntimeQuantity;
    }

    public void setFabricationTurntimeQuantity(String fabricationTurntimeQuantity) {
        this.fabricationTurntimeQuantity = fabricationTurntimeQuantity;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public boolean getNofly() {
        return nofly;
    }

    public void setNofly(boolean nofly) {
        this.nofly = nofly;
    }

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
        this.getKey().setProjectId(projectId);
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

    public String getCustomerEmail() {
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

    public boolean getItar() {
        return itar;
    }

    public void setItar(boolean itar) {
        this.itar = itar;
    }

    public String getDesignSpecification() {
        return designSpecification;
    }

    public void setDesignSpecification(String desingSpec) {
        this.designSpecification = desingSpec;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getZipfileName() {
        return zipFileName;
    }

    public void setZipfileName(String zipfileName) {
        this.zipFileName = zipfileName;
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
