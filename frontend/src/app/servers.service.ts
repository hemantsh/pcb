import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { CONFIG } from './urlpathconfig';

@Injectable()
export class FileService {
    constructor(private http: Http) { }

    //Services for Services Start

    /**
     * Service that call the backend api to get the list of Services from the database.
     */
    getServices() {
        return this.http.get(CONFIG.urlPath + 'admin/services');
    }

    /**
     * Service that call the backend api to save the new service or update the existing service into the database.
     * @param service contains service and serviceId to update and save a new service 
     */
    updateServices(service) {
        return this.http.post(CONFIG.urlPath + 'admin/services/update', service);
    }
    /**
     * Service that call the backend api to remove one or array of services from the database.
     * @param serviceTodelete takes array of services
     */
    deleteServices(serviceTodelete) {
        return this.http.put(CONFIG.urlPath + 'admin/services/delete', serviceTodelete);
    }

    //Services for Report

    /**
     * Service that call the backend api to get the unique project id's
     */
    getUniqueId() {
        return this.http.get(CONFIG.urlPath + 'admin/distinctProjectid');
    }
    /**
     * Service that call the backend api to get the ProjectReport by projectId and version.
     * @param projectId contains uniqueProjectId
     * @param version contains uniqueVersion 
     */
    getReportByIdAndVersion(projectId, version) {
        return this.http.get(CONFIG.urlPath + `fm/project/${projectId}/version/${version}`);
    }

    getReportByRnumber(rnumber) {
        return this.http.get(CONFIG.urlPath + `admin/projects/rnumber/${rnumber}`);
    }

    getReportByCustomerId(customerId) {
        return this.http.get(CONFIG.urlPath + `admin/projects/customerId/${customerId}`);
    }

    getReportByCustomerEmail(customerEmail) {
        return this.http.get(CONFIG.urlPath + `admin/projects/customerEmail/${customerEmail}`);
    }

    getReportByZipFileName(zipfilename) {
        return this.http.get(CONFIG.urlPath + `admin/projects/zipfilename/${zipfilename}`);
    }
    /**
     * Service that call the backend api to save a new project record into the database.
     * @param input takes JSON input
     */
    saveFileManagementReport(input) {
        return this.http.post(CONFIG.urlPath + 'fm/project', input);
    }

    /**
     * Service call the backend api and takes projectId to get the difference of the particular projectId.
     * @param projectId contains the projectId
     */
    getDifferences(projectId) {
        return this.http.get(CONFIG.urlPath + `fm/project/${projectId}/differences`);
    }
    //Services for FiletypeExtensions

    /**
     * Service that call the backend api to get the list of filetypeextensions from the database.
     */
    getFiletypeExtensions() {
        return this.http.get(CONFIG.urlPath + 'admin/filetypeextensions');
    }
    /**
     * Service that call the backend api to create or update filetypeextensions the database.
     * @param newData contains a filetype and its list of extensions.
     */
    createFiletypeExtensions(newData: any) {
        return this.http.post(CONFIG.urlPath + 'admin/filetypeextensions', newData)
    }
    /**
     * Service that call the backend api to delete filetypeextensions from the database having particular id and filetype.
     * @param newData contains a filetype and its list of extensions.
     */
    deleteFiletypeExtensions(newData: any) {
        return this.http.delete(CONFIG.urlPath + `admin/filetypeextensions/id/${newData.id}/filetype/${newData.file_type}`);
    }
    // Services for ServiceFiletypes
    /**
     * Service that call the backend api to fetch servicefiletypes from the database which having particular id.
     * @param id takes UUID of particular object.
     */
    getServiceFiletypesById(id) {
        return this.http.get(CONFIG.urlPath + `admin/servicefiletypes/id/${id}`);
    }
    /**
     * Service that call the backend api to create or update servicefiletypes to the database.
     * @param data contains serviceId and mapped filetype.
     */
    createServiceFiletypes(data) {
        return this.http.put(CONFIG.urlPath + 'admin/servicefiletypes/create', data);
    }
    /**
     * Service that call the backend api to delete servicefiletypes from the database.
     * @param data contains object of servicefiletypes having key and its filetype.
     */
    deleteServiceFiletypes(data) {
        return this.http.put(CONFIG.urlPath + 'admin/servicefiletypes/delete', data);
    }
}