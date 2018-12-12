import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import { FileUploadService } from '../servers.service';
@Component({
  selector: 'app-customerinputs',
  templateUrl: './customerinputs.component.html',
  styleUrls: ['./customerinputs.component.css'],
  providers:[FileUploadService]
})
export class CustomerinputsComponent implements OnInit {
  uploadForm:FormGroup;
  changeFile:any;
  constructor(private fileUploadService:FileUploadService) { }

  ngOnInit() {
    this.uploadForm= new FormGroup({
        'projectId':new FormControl(null,Validators.required),
        'customerId':new FormControl(null,Validators.required),
        'serviceType':new FormControl(null,Validators.required),
        'uploadFile':new FormControl(null,Validators.required)
      });
  }
  onSubmit(){
    console.log("This is the form:",this.uploadForm);
    var formData = new FormData();
    formData.append("projectId", this.uploadForm.value.projectId);
    formData.append("customerId", this.uploadForm.value.customerId);
    formData.append("serviceType", this.uploadForm.value.serviceType);
    formData.append("file", this.changeFile);
    let fileDetails = this.uploadForm.value;

    fileDetails.uploadFile = this.changeFile;

    console.log("File deatils", fileDetails);

    this.fileUploadService.fileUpload(formData)
    .subscribe(
      (response) =>console.log(response),
      (error) => console.log(error)

    );
  }

  public fileChangeEvent(fileInput: any){

    this.changeFile = fileInput.target.files[0];

    // console.log(fileInput);
    // var that = this;
  //   if (fileInput.target.files && fileInput.target.files[0]) {
  //     var reader = new FileReader();
  //     reader.onload = function (e : any) {
  //       that.changeFile = e.target.result;
  //     }
  //     console.log(reader.readAsArrayBuffer(fileInput.target.files[0]));
  //     //  console.log(reader.readAsDataURL(fileInput.target.files[0]));
  // }
}
}
