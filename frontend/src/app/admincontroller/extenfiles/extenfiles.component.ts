import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
import { CanComponentDeactivate } from './candeactivate-guard.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-extenfiles',
  templateUrl: './extenfiles.component.html',
  styleUrls: ['./extenfiles.component.css']
})


export class ExtenfilesComponent implements OnInit, CanComponentDeactivate {
  selectedFileTypeId = 0;
  selectedExtensionId = 0;

  fileTypeArr = [];
  filtypeList = [];
  extensionsList = [];

  extensionsArr = [];
  deleteExtnArr = [];

  changesSaved = true;
  successMsgDiv = 'hide';
  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.retriveExtnFilesTypes();
    this.retriveExtension();
    this.retriveFilesTypes();
  }

  /**
   * To reterive file types
   */
  retriveFilesTypes() {
    this.fileService.getFiletypes()
      .subscribe(
        (response: Response) => {
          this.filtypeList = response.json();
          console.log("FileTypes is fetching...", this.filtypeList);
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
  }

  /**
   * To reterive ExtnfilesTypes
   */
  retriveExtnFilesTypes() {
    this.fileService.getExtnFiles()
      .subscribe(
        (response: Response) => {
          this.fileTypeArr = response.json();
          console.log("ExtnFiles is fetching...", this.fileTypeArr);
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
  }

  /**
   * To retrive extensions
   */
  retriveExtension() {
    this.fileService.getExtensions()
      .subscribe(
        (response: Response) => {
          this.extensionsList = response.json();
          console.log("Extension is fetching...", this.extensionsList);
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );
  }

  /**
   * Retrives Array values that contains filetypeId
   * @param fileTypeId contains filetypeId 
   */
  onFileTypeSelect(fileTypeId) {
    this.fileService.getExtnFiletypesById(fileTypeId)
      .subscribe(
        (response: Response) => {
          this.extensionsArr = response.json();
          console.log("onFiletypeSelect Data is fetching...", this.extensionsArr);
        },
        (error) => console.log(error)
      );
  }

  /**
   * Adds the new extension for the selected filetype.
   * @param id contains extensionId 
   */
  onExtensionSelect(id) {
    console.log("selected extension id", id);
    console.log("Selected Filetype is :" + this.selectedFileTypeId)
    if (this.extensionsArr && this.extensionsArr.findIndex(ext => ext.extensionId == id) > -1) {
      this.selectedExtensionId = 0;
      alert("This Extension already exists! Please choose another Extension");
    } else if (this.selectedFileTypeId > 0) {
      let selectedExtension = this.extensionsList.filter(ext => ext.id == id)[0];
      let selectedFileType = this.filtypeList.filter(file => file.id == this.selectedFileTypeId)[0];
      this.extensionsArr.push({ key: { extensionId: selectedExtension.id, filetypeId: selectedFileType.id }, extension: selectedExtension.name, extensionId: selectedExtension.id, file: selectedFileType.type, filetypeId: selectedFileType.id })
      console.log("testing :" + JSON.stringify(this.extensionsArr));
    }
    this.changesSaved = false;
  }

  /**
   * Removes the extension for the selected filetype.
   * @param extension contains object that needs to be deleted 
   */
  removeExtension(extension) {
    console.log("remove Extension", extension);
    this.changesSaved = false;
    let index = this.extensionsArr.indexOf(extension);
    console.log("index", index);
    this.extensionsArr.splice(index, 1);
    this.deleteExtnArr.push(extension);
    console.log("DeleteArrayList", this.deleteExtnArr);
  }

  /**
   * Saves the selected filetype into the database.
   */
  onSaveClick() {
    console.log("this.extensionsArr", this.extensionsArr);
    this.fileService.saveExtensionFile(this.extensionsArr)
      .subscribe(
        (response: Response) => {
          console.log("this.ext", response);
          this.successMsgDiv = 'show';
          this.changesSaved = true;
        },
        (error) => console.log(error)
      );

    console.log("Array to delete: ", this.deleteExtnArr);
    console.log("Array length", this.deleteExtnArr.length);
    //This service be called and remove the extensions from the database if there is an extension to delete
    if (this.deleteExtnArr.length !== 0) {
      this.fileService.deleteExtnFiles(this.deleteExtnArr).subscribe(
        (response: Response) => console.log(response),
        (error) => console.log(error)
      );
    }

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
