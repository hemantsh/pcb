import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class FileService{
    constructor(private http: Http){}
    //File Upload Service
    fileUpload(UploadedForm){
           return this.http.post('http://localhost:8080/pcb/api/uploadAndExtract',UploadedForm);
        }
    //Services for Extensions Start
        getExtensions(){
            return this.http.get('http://localhost:8080/pcb/admin/extensions');
        }
        updateExtensions(extn){
            return this.http.post('http://localhost:8080/pcb/admin/extensions/update',extn);
        }
    //Services for Services Start
        getServices(){
            return this.http.get('http://localhost:8080/pcb/admin/services');
        }
        updateServices(service){
            return this.http.post('http://localhost:8080/pcb/admin/services/update',service);
        }
    //Services for Filetypes 
        getFiletypes(){
            return this.http.get('http://localhost:8080/pcb/admin/filetypes');
        }
        updateFiletypes(filetypes){
            return this.http.post('http://localhost:8080/pcb/admin/filetypes/update',filetypes);
        }
    //Services for Report
        getReport(){
            return this.http.get('http://localhost:8080/pcb/admin/report');
        }
    //Services for ServicesFiles
        getServiceFiles(){
            return this.http.get('http://localhost:8080/pcb/admin/servicefiles');
        }
        getServiceFilesById(id){
            return this.http.get('http://localhost:8080/pcb/admin/servicefiles/retrive/'+id);
        }
        saveServiceFiles(temp:any[]){
            return this.http.post('http://localhost:8080/pcb/admin/servicefiles/createmulti',temp);
        }
        updateServiceFiles(servicefiles){
            return this.http.post('http://localhost:8080/pcb/admin/servicefiles/update',servicefiles);
        }
    //Services for ExtensionFiles
        getExtnFiles(){
            return this.http.get('http://localhost:8080/pcb/admin/extensionfiles');
        }
        updateExtnFiles(exFT){
            return this.http.post('http://localhost:8080/pcb/admin/extensionfiles/update',exFT);
        }
        getExtnFiletypesById(id){
            return this.http.get(`http://localhost:8080/pcb/admin/extensionfiles/retrive/${id}`)
        }
        saveExtensionFile(temp:any[]){
                return this.http.post('http://localhost:8080/pcb/admin/extensionfiles/createmulti',temp);
        }
}