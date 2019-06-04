import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { VirtualTimeScheduler } from 'rxjs';

@Component({
  selector: 'app-filetypeextensions',
  templateUrl: './filetypeextensions.component.html',
  styleUrls: ['./filetypeextensions.component.css']
})
export class FiletypeextensionsComponent implements OnInit {
  fileTypeExtensions = [];
  extensions = [];
  selectedFileType: any = 0;

  selectedFileTypeObj: any;
  successMsgDiv = 'hide';
  selectedExtension = 0;
  deleteExtnArr = [];
  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.getFiletypeExtensions();
  }

  getFiletypeExtensions() {
    this.fileService.getFiletypeExtensions().subscribe(
      (response) => {
        this.fileTypeExtensions = response.json();
        console.log(this.fileTypeExtensions);
        this.processExtensions();
      },
      (error) => console.log(error)
    )
  }

  processExtensions() {
    for (let i = 0; i < this.fileTypeExtensions.length; i++) {
      for (let j = 0; j < this.fileTypeExtensions[i].extensions.length; j++) {
        this.extensions.push(this.fileTypeExtensions[i].extensions[j]);
      }
    }
  }

  onFileTypeSelect(selectedFileType) {
    this.selectedFileTypeObj = this.fileTypeExtensions.filter(file => file.file_type == selectedFileType)[0];
  }

  onExtensionSelect(selectedExtension) {
    if (this.selectedFileType == 0) {
      this.selectedExtension = 0;
      window.alert("Please Select a Filetype before selecting extensions.");

    } else {
      if (this.selectedFileTypeObj.extensions.includes(selectedExtension)) {
        this.selectedExtension = 0;
        window.alert("This extension already exists!");
      } else {
        this.selectedExtension = selectedExtension;
        this.selectedFileTypeObj.extensions.push(this.selectedExtension);
        console.log(this.selectedFileTypeObj);
      }

    }
  }

  removeExtension(extension) {
    console.log(extension);
    let index = this.selectedFileTypeObj.extensions.indexOf(extension);
    console.log(index);
    this.selectedFileTypeObj.extensions.splice(index, 1);
  }

  onSaveClick() {
    console.log(this.selectedFileTypeObj);
    this.fileService.createOrUpdateFiletypeExtensions(this.selectedFileTypeObj).subscribe(
      (response) => {
        console.log(response);
      }, (error) => console.log(error)
    );
  }
}
