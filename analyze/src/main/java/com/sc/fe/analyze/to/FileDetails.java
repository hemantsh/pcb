package com.sc.fe.analyze.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Hemant
 */
@ApiModel(value = "ExtensionFileType", description = "Table Structure of FileDetails")
@JsonInclude(Include.NON_EMPTY)
public class FileDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5547755272664009268L;

    private String name;
    private UUID version;
    private String format = "unknown"; //Gerber, odb, drill

    private boolean valid;
    private Date modifiedDate;
    private Date createDate;
    private String size;

    private String step;
    private String context;
    private String type;
    private String polarity;
    private String side;
    private int layerOrder;
    private String startName;
    private String endName;
    private String copperWeight;
    private String layerName;

    private Map<String, String> attributes; //Attributes in key:value form

    private Map<String, String> errors;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param valid - the valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the version
     */
    public UUID getVersion() {
        return version;
    }

    /**
     * @param version - the version to set
     */
    public void setVersion(UUID version) {
        this.version = version;
    }

    /**
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     *
     * @param modifiedDate - the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     *
     * @return the attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes - the attributes to set in the Map((key,value) pair)
     * form
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(String key, String value) {
        if (this.attributes == null) {
            this.attributes = new HashMap<String, String>();
        }
        attributes.put(key, value);
    }

    /**
     *
     * @return the startName
     */
    public String getStartName() {
        return startName;
    }

    /**
     *
     * @param startName - the startName to set
     */
    public void setStartName(String startName) {
        this.startName = startName;
    }

    /**
     *
     * @return the endName
     */
    public String getEndName() {
        return endName;
    }

    /**
     *
     * @param endName - the endName to set
     */
    public void setEndName(String endName) {
        this.endName = endName;
    }

    /**
     *
     * @return the copperWeight
     */
    public String getCopperWeight() {
        return copperWeight;
    }

    /**
     *
     * @param copperWeight - the copperWeight to set
     */
    public void setCopperWeight(String copperWeight) {
        this.copperWeight = copperWeight;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public int getLayerOrder() {
        return layerOrder;
    }

    public void setLayerOrder(int layerOrder) {
        this.layerOrder = layerOrder;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FileDetails other = (FileDetails) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /**
     * @return the errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

}
