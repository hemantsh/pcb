import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import {config} from './urlpathconfig';

@Injectable()
export class FileService {
    constructor(private http: Http) {}
    //File Upload Service
    fileUpload(UploadedForm) {
        return this.http.post(config.urlPath+'api/uploadAndValidate', UploadedForm);
    }
    //Services for Extensions Start
    getExtensions() {
        return this.http.get(config.urlPath+'admin/extensions');
    }
    updateExtensions(extn) {
        return this.http.post(config.urlPath+'admin/extensions/update', extn);
    }
    //Services for Services Start
    getServices() {
        return this.http.get(config.urlPath+'admin/services');
    }
    updateServices(service) {
        return this.http.post(config.urlPath+'admin/services/update', service);
    }
    //Services for Filetypes 
    getFiletypes() {
        return this.http.get(config.urlPath+'admin/filetypes');
    }
    updateFiletypes(filetypes) {
        return this.http.post(config.urlPath+'admin/filetypes/update', filetypes);
    }

    //Services for ServicesFiles
    getServiceFiles() {
        return this.http.get(config.urlPath+'admin/servicefiles');
    }
    getServiceFilesById(id) {
        return this.http.get(config.urlPath+'admin/servicefiles/retrive/' + id);
    }
    saveServiceFiles(temp: any[]) {
        return this.http.post(config.urlPath+'admin/servicefiles/createmulti', temp);
    }
    updateServiceFiles(servicefiles) {
        return this.http.post(config.urlPath+'admin/servicefiles/update', servicefiles);
    }
    //Services for ExtensionFiles
    getExtnFiles() {
        return this.http.get(config.urlPath+'admin/extensionfiles');
    }
    updateExtnFiles(exFT) {
        return this.http.post(config.urlPath+'admin/extensionfiles/update', exFT);
    }
    getExtnFiletypesById(id) {
        return this.http.get(config.urlPath+`admin/extensionfiles/retrive/${id}`)
    }
    saveExtensionFile(temp: any[]) {
        return this.http.post(config.urlPath+'admin/extensionfiles/createmulti', temp);
    }

    //Services for Report
    getUniqueId(){
        return this.http.get(config.urlPath+'report/distinctProjectid');
    }
    
    getReportByIdAndVersion(projectId,version){
        return this.http.get(config.urlPath+`report/project/${projectId}/version/${version}`);
    }

    saveFileManagementReport(input){
        return this.http.post(config.urlPath +'fm/validateAndSave',input);
    }

    getDifferences(projectId){
        return this.http.get(config.urlPath + `report/project/${projectId}/differences`);
    }
}