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

}