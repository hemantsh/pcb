import { Component, OnInit } from '@angular/core';
// import { FileService } from 'src/app/servers.service';
// import {Response} from '@angular/http';
@Component({
  selector: 'app-extenfiles',
  templateUrl: './extenfiles.component.html',
  styleUrls: ['./extenfiles.component.css']
})
export class ExtenfilesComponent implements OnInit {
  //extnFiles=[]
  constructor() { }

  ngOnInit() {
   // this.retriveExtnFiles();
  }

  // retriveExtnFiles() {

  //   this.fileService.getServiceFiles()
  //     .subscribe(
  //       (response: Response) => {
  //         this.extnFiles = response.json();
  //         console.log("Data is fetching...", this.extnFiles);
  //       },
  //       (error) => console.log(error)
  //     );
  // }


  //  updateFiles(files) {
  //   if (files.edit || files.add ) {
  //     console.log(files.edit);
  //     this.fileService.updateServiceFiles(files)
  //       .subscribe(
  //         (response: Response) => {
  //           console.log("Your data is updated", response);
  //         },
  //         (error) => console.log(error)
  //       );

  //   }
  // }

  // addFiles() {
  //   this.extnFiles.unshift({ id: this.extnFiles.length + 1, extension: null, file:null ,add: true });
  // }

  // cancelClick(files) {
  //   if (files.add) {
  //     this.extnFiles.shift();
  //   } else {
  //     files.edit = false;
  //     files.extension = files.originalExtension;
  //     files.file= files.originalFiles;
  //   }


  // }
}
