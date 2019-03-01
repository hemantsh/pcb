import { Component, OnInit, NgZone } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
import { CanFiletypesComponentDeactivate } from './candeactivate-filetype.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-filetypes',
  templateUrl: './filetypes.component.html',
  styleUrls: ['./filetypes.component.css']
})
export class FiletypesComponent implements OnInit, CanFiletypesComponentDeactivate {
  fileTypes = [];
  deletedFileTypeArray = [];
  changesSaved = true;

  constructor(private fileService: FileService, private zone: NgZone) { }
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

  /**
   * removes filetypes and stores into filetype Array
   * @param fileType takes filetype object
   */
  removefileType(fileType) {
    console.log(fileType);
    this.changesSaved = false;
    let index = this.fileTypes.indexOf(fileType);
    this.fileTypes.splice(index, 1);
    this.deletedFileTypeArray.push(fileType);
    console.log(this.deletedFileTypeArray);
    console.log("New Filetypes", this.fileTypes);
  }

  /**
   * Sends the filetype array to database.
   */
  onSave() {
    if (this.deletedFileTypeArray.length !== 0) {
      let confirm = window.confirm("Are you sure you want to delete FileTypes ?");
      if (confirm === true) {
        this.fileService.deleteFiletypes(this.deletedFileTypeArray).subscribe(
          (response: Response) => {
            console.log(response);
            this.changesSaved = true;

          },
          (error) => console.log(error)
        );
      }
      else {
        this.reloadPage();
      }
    }
  }

  reloadPage() { // click handler or similar
    this.zone.runOutsideAngular(() => {
      location.reload();
    });
  }
  /**
     * This method shows warning before navigating to another page if changes are not saved.
     */
  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!(this.changesSaved)) {
      return confirm("Do you want to discard the changes?");
    }
    else {
      return true;
    }
  }
}
