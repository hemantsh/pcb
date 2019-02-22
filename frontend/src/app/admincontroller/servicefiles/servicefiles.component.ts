import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CanServiceFilesDeactivate } from './candeactivate-servicefilesguard.service';


@Component({
  selector: 'app-servicefiles',
  templateUrl: './servicefiles.component.html',
  styleUrls: ['./servicefiles.component.css']
})

export class ServicefilesComponent implements OnInit, CanServiceFilesDeactivate {
  selectedServiceId = 0; //selectedExtensionId
  selectedFileTypeId = 0;

  fileTypeArr = [];
  fileTypeList = []; //extensionsList
  deleteFileTypeArr = [];

  serviceArr = []; //extensionsArr
  serviceFiles = [];

  changesSaved = true;
  successMsgDiv = 'hide';
  constructor(private fileService: FileService, private router: Router) { }

  ngOnInit() {
    this.retriveServiceFiles();
    this.retriveFilesTypes();
    this.retriveServices();
  }

  /** 
   * To reterive services
  */
  retriveServices() {
    this.fileService.getServices()
      .subscribe(
        (response: Response) => {
          this.serviceArr = response.json();
          console.log("Service is fetching...", this.serviceArr);
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
  }
  /** 
   * To reterive serviceFiles
  */
  retriveServiceFiles() {
    this.fileService.getServiceFiles()
      .subscribe(
        (response: Response) => {
          this.serviceFiles = response.json();
          console.log("ServiceFiles is fetching...", this.serviceFiles);
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
  }

  /** 
   * To reterive filesTypes
   */

  retriveFilesTypes() {
    this.fileService.getFiletypes()
      .subscribe(
        (response: Response) => {
          this.fileTypeList = response.json();
          console.log("Filetype Data is fetching...", this.fileTypeList);
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
  }


  onServiceSelect(serviceId) {
    this.fileService.getServiceFilesById(serviceId)
      .subscribe(
        (response: Response) => {
          this.fileTypeArr = response.json();
          console.log("ServiceFiles Data is fetching...", this.fileTypeArr);
        },
        (error) => console.log(error)
      );
  }



  onFiletypeSelect(id) {
    if (this.fileTypeArr && this.fileTypeArr.findIndex(file => file.filetypeId == id) > -1) {
      this.selectedFileTypeId = 0;
      alert("This Filtype already exists! Please choose another Filetype");
    } else if (this.selectedFileTypeId > 0) {
      console.log("service files", this.serviceFiles);
      let selectedFiletype = this.fileTypeList.filter(type => type.id == id)[0];
      let selectedService = this.serviceArr.filter(service => service.id == this.selectedServiceId)[0];
      this.fileTypeArr.push({ key: { serviceId: selectedService.id, filetypeId: selectedFiletype.id }, service: selectedService.name, serviceId: selectedService.id, file: selectedFiletype.type, filetypeId: selectedFiletype.id })
    }
    this.changesSaved = false;
  }


  removeFiletype(filetype) {
    console.log(filetype);
    this.changesSaved = false;
    let index = this.fileTypeArr.indexOf(filetype);
    this.fileTypeArr.splice(index, 1);
    this.deleteFileTypeArr.push(filetype);
    console.log("DeleteArrayList", this.deleteFileTypeArr);

  }

  onSaveClick() {
    this.fileService.saveServiceFiles(this.fileTypeArr)
      .subscribe(
        (response: Response) => {
          console.log("OnSaveResult", response);
          this.successMsgDiv = 'show';
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );

    console.log("Array to delete: ", this.deleteFileTypeArr);
    console.log("Array length", this.deleteFileTypeArr.length);
    //This service be called and remove the extensions from the database if there is an extension to delete
    if (this.deleteFileTypeArr.length !== 0) {
      this.fileService.deleteServiceFiles(this.deleteFileTypeArr).subscribe(
        (response: Response) => console.log(response),
        (error) => console.log(error)
      );
    }
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!(this.changesSaved)) {
      return confirm("Do you want to discard the changes?");
    }
    else {
      return true;
    }
  }
}
