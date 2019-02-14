package com.sc.fe.analyze.data.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ServiceFiles", description = "Table Structure of ServiceFiles")
@Table(value = "service_files")
public class ServiceFiles {

    @PrimaryKey
    private ServiceFilesPK key;

    private String service;
    private String file;

    /**
     * Gets the ServiceId.
     *
     * @return
     */
    public int getServiceId() {
        return getKey().getServiceId();
    }

    /**
     * Sets the ServiceId into the serviceId variable.
     *
     * @param serviceId takes serviceId and sets it
     */
    public void setServiceId(int serviceId) {
        this.getKey().setServiceId(serviceId);
    }

    /**
     * Gets the FiletypeId.
     *
     * @return
     */
    public int getFiletypeId() {
        return getKey().getFiletypeId();
    }

    /**
     * Sets the FiletypeId into the variable filetypeId
     *
     * @param filetypeId sets the filetypeId
     */
    public void setFiletypeId(int filetypeId) {
        this.getKey().setFiletypeId(filetypeId);
    }

    /**
     * Gets the Service.
     *
     * @return
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the Service into the variable service.
     *
     * @param service takes service and sets it
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * Gets the file.
     *
     * @return
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the file into the variable file.
     *
     * @param file takes file and sets it
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Gets the PrimaryKey, if null then initializes the ServiceFiles PrimaryKey
     *
     * @return
     */
    public ServiceFilesPK getKey() {
        if (key == null) {
            key = new ServiceFilesPK();
        }
        return key;
    }

    /**
     * Sets the ServiceFiles PrimaryKey into the variable key.
     *
     * @param key takes key and sets it
     */
    public void setKey(ServiceFilesPK key) {
        this.key = key;
    }

}
