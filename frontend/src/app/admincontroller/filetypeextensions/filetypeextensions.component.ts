import { Component, OnInit, Input } from '@angular/core';
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
  help = {
    title: 'Learn How to Map Extensions to Filetype',
    instructions: [
      'To edit extensions, choose any Filetype extensions from extensions listed.',
      'Now click on edit button corresponding to extensions of the particular filetype.',
      'Existing extensions gets converted into input box so that you can enter more extensions to map.',
      'You can enter multiple extensions which is seperated by comma ( i.e. ",") <b>for example pdf,txt,gto....</b>'
    ]
  }
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
  changeToLowerCase(data) {
    data.file_type = data.file_type.toLowerCase();
  }
  updateData(data) {
    data.extensions = data.extensions.toLowerCase();
    if (!data.edit) {
      this.fileService.createFiletypeExtensions(data)
        .subscribe(
          (response) => {
            console.log(data);
            console.log("Your data is updated", response);
          },
          (error) => console.log(error)
        );

    }
  }
  addFiletype() {
    this.changesSaved = false;
    this.fileTypeExtensions.unshift({ id: null, extensions: "", file_type: null, file: true, edit: true });
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

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!(this.changesSaved)) {
      return confirm(MESSAGE_CONST.AUTH_CHECK);
    }
    else {
      return true;
    }
  }
}
