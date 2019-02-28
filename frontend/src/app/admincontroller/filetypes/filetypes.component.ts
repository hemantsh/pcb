import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
@Component({
  selector: 'app-filetypes',
  templateUrl: './filetypes.component.html',
  styleUrls: ['./filetypes.component.css']
})
export class FiletypesComponent implements OnInit {
  fileTypes = [];
  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.retriveFileType();
  }

  /**
   * Retrives List of Filetypes from the database.
   */
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

  /**
   * Updates the already existing filetype.
   * @param fileType takes filetype object
   */
  updateFileType(fileType) {
    if (fileType.edit || fileType.add) {
      console.log(fileType.edit);
      this.fileService.updateFiletypes(fileType)
        .subscribe(
          (response: Response) => {
            console.log("Your data is updated", response);
            console.log("->>>>>", fileType.type);
          },
          (error) => console.log(error)
        );

    }
  }

  /**
   * Adds a textbox into the list for adding new filetype. 
   */
  addFileType() {
    this.fileTypes.unshift({ id: this.fileTypes.length + 1, type: null, add: true });
  }
  /**
   * Removes the textbox.
   * @param fileType contains filetype property
   */
  cancelClick(fileType) {
    if (fileType.add) {
      this.fileTypes.shift();
    } else {
      fileType.edit = false;
      fileType.type = fileType.originalData
    }
  }
}
