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


  updateExtn(extn) {
    if (extn.edit || extn.add ) {
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

  addExtn() {
    this.extns.unshift({ id: this.extns.length + 1, name: null, add: true });
  }

  cancelClick(extn) {
    if (extn.add) {
      this.extns.shift();
    } else {
      extn.edit = false;
      extn.name = extn.originalData
    }


  }



  // startEdit(i: number, id: number, title: string, state: string, url: string, created_at: string, updated_at: string) {
  //   this.id = id;
  //   // index row is used just for debugging proposes and can be removed
  //   this.index = i;
  //   console.log(this.index);
  //   const dialogRef = this.dialog.open(EditDialogComponent, {
  //     data: {id: id, title: title, state: state, url: url, created_at: created_at, updated_at: updated_at}
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     if (result === 1) {
  //       // When using an edit things are little different, firstly we find record inside DataService by id
  //       const foundIndex = this.exampleDatabase.dataChange.value.findIndex(x => x.id === this.id);
  //       // Then you update that record using data from dialogData (values you enetered)
  //       this.exampleDatabase.dataChange.value[foundIndex] = this.dataService.getDialogData();
  //       // And lastly refresh table
  //       this.refreshTable();
  //     }
  //   });
  // }

}
