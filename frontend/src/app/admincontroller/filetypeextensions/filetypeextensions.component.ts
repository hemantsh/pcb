import { Component, OnInit, OnChanges } from '@angular/core';
import { FileService } from 'src/app/servers.service';


@Component({
  selector: 'app-filetypeextensions',
  templateUrl: './filetypeextensions.component.html',
  styleUrls: ['./filetypeextensions.component.css']
})
export class FiletypeextensionsComponent implements OnInit, OnChanges {
  fileTypeExtensions = [];
  extensions = [];
  selectedFileType: any = 0;

  selectedFileTypeObj: any;
  successMsgDiv = 'hide';
  // selectedExtension = 0;

  // below variables are for Multi select dropdoewn
  cities = [];
  selectedItems = [];
  dropdownSettings = {}
  ShowFilter = false;
  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.getFiletypeExtensions();
    // Below code is for Multi select Dropdown
    // this.extensions = [
    // ];
    // this.selectedItems = [];
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 50,
      allowSearchFilter: this.ShowFilter
    };
  }
  ngOnChanges() {
    this.extensions = [
    ];
    this.selectedItems = [];
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 50,
      allowSearchFilter: this.ShowFilter
    };
    this.onFileTypeSelect(this.selectedFileType);
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

  // processExtensions() {
  //   for (let i = 0; i < this.fileTypeExtensions.length; i++) {
  //     for (let j = 0; j < this.fileTypeExtensions[i].extensions.length; j++) {
  //       this.extensions.push(this.fileTypeExtensions[i].extensions[j]);
  //     }
  //   }
  // }

  onFileTypeSelect(selectedFileType) {
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
  }

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

  onItemSelect(item: any) {
    console.log('onItemSelect', item);
  }
  onSelectAll(items: any) {
    console.log('onSelectAll', items);
  }
}
