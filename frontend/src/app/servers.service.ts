import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class FileService{
    constructor(private http: Http){}
    fileUpload(UploadedForm){
           return this.http.post('http://localhost:8080/pcb/api/uploadAndExtract',UploadedForm);
        }

        getExtensions(){
            return this.http.get('http://localhost:8080/pcb/admin/extensions');
        }
        updateExtensions(extn){
            return this.http.post('http://localhost:8080/pcb/admin/extensions/update',extn);
        }

        getServices(){
            return this.http.get('http://localhost:8080/pcb/admin/services');
        }
        updateServices(service){
            return this.http.post('http://localhost:8080/pcb/admin/services/update',service);
        }
        getFiletypes(){
            return this.http.get('http://localhost:8080/pcb/admin/filetypes');
        }
        updateFiletypes(filetypes){
            return this.http.post('http://localhost:8080/pcb/admin/filetypes/update',filetypes);
        }
        getReport(){
            return this.http.get('http://localhost:8080/pcb/admin/report');
        }
        getServiceFiles(){
            return this.http.get('http://localhost:8080/pcb/admin/servicefiles');
        }
        updateServiceFiles(servicefiles){
            return this.http.post('http://localhost:8080/pcb/admin/servicefiles/update',servicefiles);
        }
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
                return this.http.post('http://localhost:8080/pcb/admin/extensionfiles/create',temp);
        }
}