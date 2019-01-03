import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class FileService{
    constructor(private http: Http){}
    fileUpload(UploadedForm){
        // var headers = new Headers();
        // headers.append('Access-Control-Allow-Headers', 'X-Requested-With,content-type');
        // headers.append("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT,OPTIONS");
        // headers.append('Access-Control-Allow-Origin', '*');
        // headers.append('Access-Control-Allow-Credentials', 'true');
        // console.log("file to upload", JSON.stringify(UploadedForm));
           return this.http.post('http://localhost:8080/pcb/api/uploadAndExtract',UploadedForm);
        }

        getExtensions(){
            return this.http.get('http://localhost:8080/pcb/admin/extensions');
        }
    

}