import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { CanFiletypeExtensionsComponentDeactivate } from './can-deactivate-filetype-extn.service';
import { Observable } from 'rxjs';
import { MESSAGE_CONST } from '../../error-messages';
@Component({
  selector: 'app-filetypeextensions',
  templateUrl: './filetypeextensions.component.html',
  styleUrls: ['./filetypeextensions.component.css']
})
export class FiletypeextensionsComponent implements OnInit, CanFiletypeExtensionsComponentDeactivate {
  fileTypeExtensions = [];
  successMsgDiv = 'hide';
  deleteObj;
  changesSaved = true;

  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.getFiletypeExtensions();
  }

  getFiletypeExtensions() {
    this.fileService.getFiletypeExtensions().subscribe(
      (response) => {
        this.fileTypeExtensions = response.json();
        console.log(this.fileTypeExtensions);
      },
      (error) => console.log(error)
    )
  }

  updateData(data) {
    if (!data.edit) {
      console.log(data.edit);
      this.fileService.createFiletypeExtensions(data)
        .subscribe(
          (response) => {
            console.log("Your data is updated", response);
          },
          (error) => console.log(error)
        );

    }
  }
  addFiletype() {
    this.changesSaved = false;
    this.fileTypeExtensions.unshift({ id: null, extensions: null, file_type: null, file: true, edit: true });
  }
  removeExtension(data) {
    if (data.file_type == null || data.extensions == null) {
      let index = this.fileTypeExtensions.indexOf(data);
      this.deleteObj = this.fileTypeExtensions.splice(index, 1)[0];
      this.changesSaved = true;
    } else {
      let cnfrm = window.confirm("Are you sure you want to delete ?")
      let index = this.fileTypeExtensions.indexOf(data);
      if (cnfrm == true) {
        this.deleteObj = this.fileTypeExtensions.splice(index, 1)[0];

        this.fileService.deleteFiletypeExtensions(this.deleteObj).subscribe(
          (response) => console.log(response),
          (error) => console.log(error)
        )
      }
    }

  }


  onSaveClick() {
    // Right now we are just pushing the top value of the stack.
    console.log(this.fileTypeExtensions[0]);
    this.fileService.createFiletypeExtensions(this.fileTypeExtensions[0]).subscribe(
      (response) => {
        console.log(response);
      }, (error) => console.log(error)
    );
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!(this.changesSaved)) {
      return confirm(MESSAGE_CONST.AUTH_CHECK);
    }
    else {
      return true;
    }
  }
}
