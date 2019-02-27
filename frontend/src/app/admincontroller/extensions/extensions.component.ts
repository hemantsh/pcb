import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
@Component({
  selector: 'app-extensions',
  templateUrl: './extensions.component.html',
  styleUrls: ['./extensions.component.css']
})
export class ExtensionsComponent implements OnInit {

  extns = [];
  // displayedColumns: string[] = ['Extensions'];

  constructor(private fileService: FileService) { }
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
}
