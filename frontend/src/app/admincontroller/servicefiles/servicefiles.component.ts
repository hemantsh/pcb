import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import {Response} from '@angular/http';

@Component({
  selector: 'app-servicefiles',
  templateUrl: './servicefiles.component.html',
  styleUrls: ['./servicefiles.component.css']
})
export class ServicefilesComponent implements OnInit {
  serviceFiles=[];
  constructor(private fileService:FileService) { }

  ngOnInit() {
    this.retriveFiles();
  }

  retriveFiles() {

    this.fileService.getServiceFiles()
      .subscribe(
        (response: Response) => {
          this.serviceFiles = response.json();
          console.log("Data is fetching...", this.serviceFiles);
        },
        (error) => console.log(error)
      );
  }


   updateFiles(files) {
    if (files.edit || files.add ) {
      console.log(files.edit);
      this.fileService.updateServiceFiles(files)
        .subscribe(
          (response: Response) => {
            console.log("Your data is updated", response);
          },
          (error) => console.log(error)
        );

    }
  }

  addFiles() {
    this.serviceFiles.unshift({ id: this.serviceFiles.length + 1, service: null, file:null ,add: true });
  }

  cancelClick(files) {
    if (files.add) {
      this.serviceFiles.shift();
    } else {
      files.edit = false;
      files.service = files.originalService;
      files.file= files.originalFiles;
    }


  }
}
