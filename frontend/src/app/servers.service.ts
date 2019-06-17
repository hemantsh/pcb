import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { config } from './urlpathconfig';

@Injectable()
export class FileService {
    constructor(private http: Http) { }

    //File Upload Service
    fileUpload(UploadedForm) {
        return this.http.post(config.urlPath + 'api/uploadAndValidate', UploadedForm);
    }

    //Services for Extensions Start

    /**
     * Service that call the backend api to get the list of Extensions from the database.
     */
    getExtensions() {
        return this.http.get(config.urlPath + 'admin/extensions');
    }

    /**
     * Service that call the backend api to save the new extension or update the existing extension into the database.
     * @param extn contains exntension and extension id to update and save a new extension
     */
    updateExtensions(extn) {
        return this.http.post(config.urlPath + 'admin/extensions/update', extn);
    }

    /**
     * Service that call the backend api to remove one or array of extensions from the database.
     * @param extntodelete takes array of extensions
     */
    deleteExtensions(extntodelete) {
        return this.http.put(config.urlPath + 'admin/extensions/delete', extntodelete);
    }

    //Services for Services Start

    /**
     * Service that call the backend api to get the list of Services from the database.
     */
    getServices() {
        return this.http.get(config.urlPath + 'admin/services');
    }

    /**
     * Service that call the backend api to save the new extension or update the existing service into the database.
     * @param service contains service and serviceId to update and save a new service 
     */
    updateServices(service) {
        return this.http.post(config.urlPath + 'admin/services/update', service);
    }
    /**
     * Service that call the backend api to remove one or array of services from the database.
     * @param serviceTodelete takes array of services
     */
    deleteServices(serviceTodelete) {
        return this.http.put(config.urlPath + 'admin/services/delete', serviceTodelete);
    }

    //Services for Filetypes 

    /**
     * Service that call the backend api to get the list of Filetypes from the database. 
     */
    getFiletypes() {
        return this.http.get(config.urlPath + 'admin/filetypes');
    }

    /**
     * Service that call the backend api to save the new filetype or update the existing filetype into the database.
     * @param filetypes contains filetypeId and filetype to update and save a new filetype
     */
    updateFiletypes(filetypes) {
        return this.http.post(config.urlPath + 'admin/filetypes/update', filetypes);
    }
    /**
     * Service that call tha backend api to remove one or array of filetypes from the database.
     * @param filetypesTodelete takes array of filetypes
     */
    deleteFiletypes(filetypesTodelete) {
        return this.http.put(config.urlPath + 'admin/filetypes/delete', filetypesTodelete);
    }

    //Services for ServicesFiles

    /**
     * Service that call the backend api to get the list of servicefiles from the database.
     */
    getServiceFiles() {
        return this.http.get(config.urlPath + 'admin/servicefiles');
    }

    /**
     * Service that call the backend api to get the servicefiles which has the serviceId.
     * @param id contains the serviceId 
     */
    getServiceFilesById(id) {
        return this.http.get(config.urlPath + 'admin/servicefiles/retrive/' + id);
    }

    /**
     * Service that call the backend api to save the servicefiles or update the servciefiles. 
     * @param data contains array of servicefiles
     */
    saveServiceFiles(data: any[]) {
        return this.http.post(config.urlPath + 'admin/servicefiles/createmulti', data);
    }
    /**
     * Service that call the backend api to save or update a new servicefile.
     * @param servicefiles contains servicefiles data
     */
    updateServiceFiles(servicefiles) {
        return this.http.post(config.urlPath + 'admin/servicefiles/update', servicefiles);
    }

    /**
     * Service that call the backend api to delete the servicefile.
     * @param servicefilesDeleteData contains servicefiles that needs to be deleted  
     */
    deleteServiceFiles(servicefilesDeleteData) {
        return this.http.put(config.urlPath + 'admin/servicefiles/delete', servicefilesDeleteData)
    }

    //Services for ExtensionFiles

    /**
     * Service that call the backend api to get the ExtnensionFiles from the database.
     */
    getExtnFiles() {
        return this.http.get(config.urlPath + 'admin/extensionfiles');
    }
    /**
     * Service that call the backend api to save or update the extensionfiles into the database.
     * @param exFT contains extensionfiles data
     */
    updateExtnFiles(exFT) {
        return this.http.post(config.urlPath + 'admin/extensionfiles/update', exFT);
    }

    /**
     * Service that call the backend api to get the ExtensionFiles data by filetypeId from the database. 
     * @param id contains filetypeId
     */
    getExtnFiletypesById(id) {
        return this.http.get(config.urlPath + `admin/extensionfiles/retrive/${id}`);
    }

    /**
     * Service that call the backend api to save or update the extensionfiles.
     * @param data contains array of exntesionfiles
     */
    saveExtensionFile(data: any[]) {
        return this.http.post(config.urlPath + 'admin/extensionfiles/createmulti', data);
    }

    /**
     * Service that call the backend api to delete the extensionfiles that is passed.
     * @param extnfilesDeleteData contains extensionfiles that needs to be deleted  
     */
    deleteExtnFiles(extnfilesDeleteData) {
        return this.http.put(config.urlPath + 'admin/extensionfiles/delete', extnfilesDeleteData);
    }

    //Services for Report

    /**
     * Service that call the backend api to get the unique project id's
     */
    getUniqueId() {
        return this.http.get(config.urlPath + 'fm/distinctProjectid');
    }
    /**
     * Service that call the backend api to get the ProjectReport by projectId and version.
     * @param projectId contains uniqueProjectId
     * @param version contains uniqueVersion 
     */
    getReportByIdAndVersion(projectId, version) {
        return this.http.get(config.urlPath + `fm/project/${projectId}/version/${version}`);
    }

    /**
     * Service that call the backend api to save a new project record into the database.
     * @param input takes JSON input
     */
    saveFileManagementReport(input) {
        return this.http.post(config.urlPath + 'fm/project', input);
    }

    /**
     * Service call the backend api and takes projectId to get the difference of the particular projectId.
     * @param projectId contains the projectId
     */
    getDifferences(projectId) {
        return this.http.get(config.urlPath + `fm/project/${projectId}/differences`);
    }

    getFiletypeExtensions() {
        return this.http.get(config.urlPath + 'admin/filetypeextensions');
    }

    createFiletypeExtensions(newData: any) {
        return this.http.post(config.urlPath + 'admin/filetypeextensions', newData)
    }
    deleteFiletypeExtensions(newData:any) {
        return this.http.delete(config.urlPath + `admin/filetypeextensions/${newData.id}`);
    }
}