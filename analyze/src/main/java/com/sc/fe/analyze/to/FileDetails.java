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
 * @author Hemant
 */
@ApiModel(value = "ExtensionFileType", description = "Table Structure of FileDetails")
@JsonInclude(Include.NON_EMPTY)
public class FileDetails implements Serializable {

    private static final long serialVersionUID = 5547755272664009268L;

    private String name;
    private UUID version;
    private String format = "unknown"; //Gerber, odb, drill

    private boolean valid;
    private Date modifiedDate;
    private Date createDate;
    private Date fileDate;
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
    private Map<String, String> errors;     //Errors in key:value form

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name into the name instance variable
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the date of file
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * Sets the Date of the file at which date it is created
     *
     * @param fileDate date of the file
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     *
     * @return the valid value of the file
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the file is valid or not into the valid instance variable
     *
     * @param valid the file valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * It returns the date of the file on which the file was created
     *
     * @return the date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * It sets the date of the file on which the file was created into the
     * createDate instance variable
     *
     * @param createDate it shows current date and time
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the version of the project
     */
    public UUID getVersion() {
        return version;
    }

    /**
     * It sets the version of the project into the version instance variable
     *
     * @param version the version to set
     */
    public void setVersion(UUID version) {
        this.version = version;
    }

    /**
     * It returns the date of the file on which the file was modified/update
     *
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * It sets the date of the file on which the file was modified/update
     *
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     *
     * @return the attributes of the file
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes the attributes to set in the Map((key,value) pair) form
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * It sets the attributes of file into the attribute instance variable
     *
     * @param key the name of the file
     * @param value the values of the key(file)
     */
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
     * It sets the startName into the startName instance variable
     *
     * @param startName the startName to set
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
     * It sets the endName into the endName instance variable
     *
     * @param endName the endName to set
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
     * It sets the copperWeight into the copperWeight instance variable
     *
     * @param copperWeight the copperWeight to set
     */
    public void setCopperWeight(String copperWeight) {
        this.copperWeight = copperWeight;
    }

    /**
     *
     * @return the polarity
     */
    public String getPolarity() {
        return polarity;
    }

    /**
     * It sets the polarity into the polarity instance variable
     *
     * @param polarity the polarity to set
     */
    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    /**
     *
     * @return the step
     */
    public String getStep() {
        return step;
    }

    /**
     * It sets the step into the step instance variable
     *
     * @param step the step to set
     */
    public void setStep(String step) {
        this.step = step;
    }

    /**
     *
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * It sets the context into the context instance variable
     *
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     *
     * @return the format of file
     */
    public String getFormat() {
        return format;
    }

    /**
     * It sets the format into the format instance variable
     *
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     *
     * @return the type of file
     */
    public String getType() {
        return type;
    }

    /**
     * It sets the type of file into the type instance variable
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return the side of file
     */
    public String getSide() {
        return side;
    }

    /**
     * It sets the side of the file into the side instance variable
     *
     * @param side the side to set
     */
    public void setSide(String side) {
        this.side = side;
    }

    /**
     *
     * @return the layer Sequence
     */
    public int getLayerOrder() {
        return layerOrder;
    }

    /**
     * It sets the layer Sequence of file into the layerSequence instance
     * variable
     *
     * @param layerOrder the layer Sequence to set
     */
    public void setLayerOrder(int layerOrder) {
        this.layerOrder = layerOrder;
    }

    /**
     *
     * @return the layerName of the file
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     * It sets the Name of the layer into the layerName instance variable
     *
     * @param layerName the layerName to set
     */
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
     * @return the set of errors of file in the Map((key,value) pair) form
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Sets the errors into the errors instance variable
     *
     * @param errors the errors to set in the Map((key,value) pair) form
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * @return the size of file
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
