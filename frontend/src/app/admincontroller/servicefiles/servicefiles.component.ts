import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {CanServiceFilesDeactivate} from './candeactivate-servicefilesguard.service';


@Component({
  selector: 'app-servicefiles',
  templateUrl: './servicefiles.component.html',
  styleUrls: ['./servicefiles.component.css']
})

export class ServicefilesComponent implements OnInit,CanServiceFilesDeactivate {
  selectedServiceId = 0; //selectedExtensionId
  selectedFileTypeId = 0;

  fileTypeArr = [];
  fileTypeList = []; //extensionsList

  serviceArr = []; //extensionsArr
  changesSaved = true;

  divStyle= 'hide';
  constructor(private fileService: FileService, private router: Router) { }

  ngOnInit() {
    this.retriveServiceFiles();
    this.retriveFilesTypes();
  }

  /** 
   * To reterive serviceFiles
  */
  retriveServiceFiles() {
    this.fileService.getServiceFiles()
      .subscribe(
        (response: Response) => {
          this.serviceArr = response.json();
          console.log("ServiceFiles is fetching...", this.serviceArr);
          this.changesSaved=true;
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
          this.changesSaved=true;
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
          this.changesSaved=false;
        },
        (error) => console.log(error)
      );
  }



  onFiletypeSelect(id) {
    if (this.fileTypeArr && this.fileTypeArr.findIndex(file => file.filetypeId == id) > -1) {
      this.selectedFileTypeId = 0;
      alert("This Filtype already exists! Please choose another Filetype");
    } else if (this.selectedFileTypeId > 0) {
      let selectedFiletype = this.fileTypeList.filter(type => type.id == id)[0];
      let selectedService = this.serviceArr.filter(service => service.serviceId == this.selectedServiceId)[0];
      this.fileTypeArr.push({ key: { serviceId: selectedService.serviceId, filetypeId: selectedFiletype.id }, service: selectedService.service, serviceId: selectedService.serviceId, file: selectedFiletype.type, filetypeId: selectedFiletype.id })
    }
      this.changesSaved=false;
  }


  removeFiletype(filetype) {
    console.log(filetype);
    let index = this.fileTypeArr.indexOf(filetype);
    this.fileTypeArr.splice(index, 1);
    
  }

  onSaveClick() {
    this.fileService.saveServiceFiles(this.fileTypeArr)
      .subscribe(
        (response: Response) => {
          console.log("OnSaveResult", response);
          this.divStyle='show';
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
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
