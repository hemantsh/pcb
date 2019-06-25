import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
import { CanServiceFileTypesComponentDeactivate } from './candeactivate.servicefiletypes.service';
import { Observable } from 'rxjs';
import { MESSAGE_CONST } from '../../error-messages';
@Component({
  selector: 'app-servicefiletypes',
  templateUrl: './servicefiletypes.component.html',
  styleUrls: ['./servicefiletypes.component.css']
})
export class ServicefiletypesComponent implements OnInit, CanServiceFileTypesComponentDeactivate {
  selectedServiceId = 0; //selectedExtensionId
  selectedFiletype = 0;
  fileTypeArr = [];

  deleteFileTypeArr = [];

  serviceArr = []; //extensionsArr
  filetypesList = [];
  filetypes = [];
  changesSaved = true;
  successMsgDiv = 'hide';
  help = {
    title: 'Learn How to Map Filetypes to Services',
    instructions: [
      "First choose any service from services listed in the dropdown.",
      "After that you can choose filetype from the filetypes listed in the dropdown.",
      "If the filetype is already exists in the service, software will show an error otherwise you can see the selected filetype added to the selected services filetype list.",
      "After that you have to click on <b>Save</b> to make changes parmanent otherwise changes won't affect in the database."
    ]
  }
  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.retriveServicesAndFiletypes();
  }

  /**
   * To reterive list of services
   */
  retriveServicesAndFiletypes() {
    this.fileService.getServices()
      .subscribe(
        (response: Response) => {
          this.serviceArr = response.json();
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
    this.fileService.getFiletypeExtensions().subscribe(
      (response: Response) => {
        this.filetypesList = response.json();
        this.getFiletypes();
      },
      (error) => console.log(error)
    );

  }

  onServiceSelect(id) {
    console.log(id);
    this.fileService.getServiceFiletypesById(id).subscribe(
      (response: Response) => {
        this.fileTypeArr = response.json();
        console.log(this.fileTypeArr);
      },
      (error: Error) => console.log(error)
    );

  }

  getFiletypes() {
    this.filetypesList.forEach(row => {
      this.filetypes.push(row.file_type);
    });

  }

  onFiletypeSelect() {
    if (this.selectedServiceId == 0) {
      this.selectedFiletype = 0;
      window.alert("Please select a service first.");

    } else {

      let flag = false;
      this.fileTypeArr.forEach(row => {
        if (row.fileType == this.selectedFiletype) {
          flag = true;
        }
      });
      if (flag == false) {
        this.changesSaved = false;
        this.fileTypeArr.push(
          {
            key: {
              "serviceid": this.selectedServiceId,
              "filetype": this.selectedFiletype
            },
            "fileType": this.selectedFiletype
          }
        );
      } else {
        window.alert("FileType already exists.");
      }
    }
  }

  onSave() {
    console.log(this.fileTypeArr);
    this.fileService.createServiceFiletypes(this.fileTypeArr).subscribe(
      (response: Response) => {
        if (response.status == 200) {
          this.changesSaved = true;
          this.successMsgDiv = 'show';
        } console.log(response);
      },
      (error) => console.log(error)
    );

    if (this.deleteFileTypeArr.length !== 0) {
      let cnfrm = confirm(MESSAGE_CONST.DELETE_AUTH_CHECK);
      if (cnfrm == true) {
        this.fileService.deleteServiceFiletypes(this.deleteFileTypeArr).subscribe(
          (response: Response) => {
            console.log(response)
            if (response.status == 200) {
              this.changesSaved = true;
            }
          },
          (error) => console.log(error)
        );
      }
    }
  }

  removeFiletype(filetype) {
    this.changesSaved = false;
    console.log(filetype);
    let index = this.fileTypeArr.indexOf(filetype);
    this.fileTypeArr.splice(index, 1);
    this.deleteFileTypeArr.push(filetype);
    console.log(this.deleteFileTypeArr);
  }

  /**
   * This method shows warning before navigating to another page if changes are not saved.
   */
  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!(this.changesSaved)) {
      return confirm(MESSAGE_CONST.AUTH_CHECK);
    }
    else {
      return true;
    }
  }

}
