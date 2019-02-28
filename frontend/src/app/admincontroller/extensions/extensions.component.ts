import { Component, OnInit, NgZone } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
import { Observable } from 'rxjs';
import { CanExtensionsComponentDeactivate } from './can-deactivate-extn.service';
@Component({
  selector: 'app-extensions',
  templateUrl: './extensions.component.html',
  styleUrls: ['./extensions.component.css']
})
export class ExtensionsComponent implements OnInit, CanExtensionsComponentDeactivate {

  extns = [];
  deletedExtnArray = [];
  changesSaved = true;

  constructor(private fileService: FileService, private zone: NgZone) { }
  ngOnInit() {
    this.retriveExtn();
  }

  /**
   * Retrives List of extensions from the database.
   */
  retriveExtn() {

    this.fileService.getExtensions()
      .subscribe(
        (response: Response) => {
          this.extns = response.json();
          this.changesSaved = true;
          console.log("Data is fetching...", this.extns);
        },
        (error) => console.log(error)
      );
  }

  /**
   * Updates the already existing extension. 
   * @param extn takes extension object
   */
  updateExtn(extn) {
    if (extn.edit || extn.add) {
      console.log(extn.edit);
      this.fileService.updateExtensions(extn)
        .subscribe(
          (response: Response) => {
            console.log("Your data is updated", response);
            // this.reloadPage();
            this.changesSaved = true;
          },
          (error) => console.log(error)
        );

    }
  }

  /**
   * Adds a textbox into the list for adding new extension. 
   */
  addExtn() {
    this.extns.unshift({ id: this.extns.length + 1, name: null, add: true });
  }

  /**
   * Removes the textbox.
   * @param extn contains extension property
   */
  cancelClick(extn) {
    if (extn.add) {
      this.extns.shift();
    } else {
      extn.edit = false;
      extn.name = extn.originalData
    }
  }
  /**
   * Removes the selected extensions.
   * @param extn contains object that needs to be deleted 
   */
  removeExtension(extension) {
    this.changesSaved = false;
    console.log(extension);
    let index = this.extns.indexOf(extension);
    this.extns.splice(index, 1);
    this.deletedExtnArray.push(extension);
    console.log("Extensions to delete:", this.deletedExtnArray);
    console.log("Remaining Extensions", this.extns);
  }
  /**
    * Sends the Extensions array to database.
    */
  onSave() {
    if (this.deletedExtnArray.length !== 0) {
      let confirm = window.confirm("Are you sure you want to delete extensions ?");
      if (confirm === true) {
        this.fileService.deleteExtensions(this.deletedExtnArray).subscribe(
          (response: Response) => {
            console.log(response);
            this.changesSaved = true;
          },
          (error) => console.log(error)
        );
      } else {
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
