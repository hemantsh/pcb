import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';


@Component({
  selector: 'app-filetypeextensions',
  templateUrl: './filetypeextensions.component.html',
  styleUrls: ['./filetypeextensions.component.css']
})
export class FiletypeextensionsComponent implements OnInit {
  fileTypeExtensions = [];
  extensions = [];

  selectedFileTypeObj: any;
  successMsgDiv = 'hide';


  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.getFiletypeExtensions();
    // Below code is for Multi select Dropdown
    // this.extensions = [
    // ];
    // this.selectedItems = [];
  }

  getFiletypeExtensions() {
    this.fileService.getFiletypeExtensions().subscribe(
      (response) => {
        this.fileTypeExtensions = response.json();
        console.log(this.fileTypeExtensions);
        // this.processExtensions();
      },
      (error) => console.log(error)
    )
  }

  updateData(data) {
    if (!data.edit) {
      console.log(data.edit);
      this.fileService.createOrUpdateFiletypeExtensions(data)
        .subscribe(
          (response) => {
            console.log("Your data is updated", response);
          },
          (error) => console.log(error)
        );

    }
  }

  removeExtension(extension){
    console.log(extension);
    let index = this.fileTypeExtensions.indexOf(extension);
    console.log(index);
    this.fileTypeExtensions.splice(index,1);
  }
  // processExtensions() {
  //   for (let i = 0; i < this.fileTypeExtensions.length; i++) {
  //     for (let j = 0; j < this.fileTypeExtensions[i].extensions.length; j++) {
  //       this.extensions.push(this.fileTypeExtensions[i].extensions[j]);
  //     }
  //   }
  // }

  /*onFileTypeSelect(selectedFileType) {
    this.selectedItems=[];
    this.extensions=[];
    this.selectedFileType = selectedFileType;
    this.selectedFileTypeObj = this.fileTypeExtensions.filter(file => file.file_type == selectedFileType)[0];
    console.log(this.selectedFileTypeObj);
    for (let i = 0; i < this.selectedFileTypeObj.extensions.length; i++) {
      this.extensions.push({ item_id: i + 1, item_text: this.selectedFileTypeObj.extensions[i] });
      this.selectedItems.push({ item_id: i + 1, item_text: this.selectedFileTypeObj.extensions[i] });
    }
    console.log(this.extensions);
    console.log(this.selectedItems);
  }*/

  /*onExtensionSelect(selectedExtension) {
    if (this.selectedFileType == 0) {
      this.selectedExtension = 0;
      window.alert("Please Select a Filetype before selecting extensions.");

    } else {
      if (this.selectedFileTypeObj.extensions.includes(selectedExtension)) {
        this.selectedExtension = 0;
        window.alert("This extension already exists!");
      } else {
        this.selectedExtension = selectedExtension;
        // this.selectedFileTypeObj.extensions.push(this.selectedExtension);
        this.selectedFileTypeObj.extensions.push(this.selectedExtension);
        console.log(this.selectedFileTypeObj);
      }
    }
  }*/

  /*removeExtension(extension) {
    console.log(extension);
    let index = this.selectedFileTypeObj.extensions.indexOf(extension);
    console.log(index);
    this.selectedFileTypeObj.extensions.splice(index, 1);
  }*/

  onSaveClick() {
    console.log(this.selectedFileTypeObj);
    // this.fileService.createOrUpdateFiletypeExtensions(this.selectedFileTypeObj).subscribe(
    //   (response) => {
    //     console.log(response);
    //   }, (error) => console.log(error)
    // );
  }
}
