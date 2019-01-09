import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import {Response} from '@angular/http';
@Component({
  selector: 'app-extenfiles',
  templateUrl: './extenfiles.component.html',
  styleUrls: ['./extenfiles.component.css']
})
export class ExtenfilesComponent implements OnInit {
  fileTypes=0;
  extnFilesArr = [];
  extnType=0;
  extnTypeArr=[];
  filterTypeArr=[];

  constructor(private fileService:FileService) { }

  ngOnInit() {
   this.retriveExtnFiles();
  }

  retriveExtnFiles() {

    this.fileService.getExtnFiles()
      .subscribe(
        (response: Response) => {
          this.extnFilesArr = response.json();
          this.extnTypeArr=response.json();
           console.log("Data is fetching...", this.extnFilesArr);
        },
        (error) => console.log(error)
      );

      // this.fileService.getExtnFiles()
      // .subscribe(
      //   (response: Response) => {
      //     this.extnTypeArr = response.json();
      //     console.log("Data is fetching...", this.extnTypeArr);
      //   },
      //   (error) => console.log(error)
      // );
  }


   updateFiles(files) {
    if (files.edit || files.add ) {
      console.log(files.edit);
      this.fileService.updateExtnFiles(files)
        .subscribe(
          (response: Response) => {
            console.log("Your data is updated", response);
          },
          (error) => console.log(error)
        );

    }
  }
  onSelect(fileTypes){
    console.log(fileTypes);
    this.fileService.getExtnFiletypesById(fileTypes)
      .subscribe(
        (response: Response) => {
          this.filterTypeArr = response.json();
           console.log("Data is fetching...", this.filterTypeArr);
        },
        (error) => console.log(error)
      );
  }
  // addExtn() {
  //   this.extnTypeArr.unshift({ id: this.extnTypeArr.length + 1, extension: null, file:null ,add: true });
  // }

  cancelClick(files) {
    if (files.add) {
      this.extnFilesArr.shift();
    } else {
      files.edit = false;
      files.extension = files.originalExtension;
      files.file= files.originalFiles;
    }
 
  }
}
