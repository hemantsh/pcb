import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import {Response} from '@angular/http';
@Component({
  selector: 'app-filetypes',
  templateUrl: './filetypes.component.html',
  styleUrls: ['./filetypes.component.css']
})
export class FiletypesComponent implements OnInit {
fileTypes=[];
  constructor(private fileService:FileService) { }

  ngOnInit() {
    this.retriveFileType();
  }

  retriveFileType() {

    this.fileService.getFiletypes()
      .subscribe(
        (response: Response) => {
          this.fileTypes = response.json();

          console.log("Data is fetching...", this.fileTypes);
        },
        (error) => console.log(error)
      );
  }


  updateFileType(fileType) {
    if (fileType.edit || fileType.add ) {
      console.log(fileType.edit);
      this.fileService.updateFiletypes(fileType)
        .subscribe(
          (response: Response) => {
            console.log("Your data is updated", response);
            console.log("->>>>>",fileType.type);
          },
          (error) => console.log(error)
        );

    }
  }

  addFileType() {
    this.fileTypes.unshift({ id: this.fileTypes.length + 1, type : null, add: true });
  }

  cancelClick(fileType) {
    if (fileType.add) {
      this.fileTypes.shift();
    } else {
      fileType.edit = false;
      fileType.type = fileType.originalData
    }
  }
}
