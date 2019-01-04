import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import {Response} from '@angular/http';
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  objectKeys = Object.keys;
reports=[];
  constructor(private fileService:FileService) { }

  ngOnInit() {
    this.retriveReport();
  }

  retriveReport() {

    this.fileService.getReport()
      .subscribe(
        (response: Response) => {
          this.reports = response.json();

          console.log("Data is fetching...", this.reports);
        },
        (error) => console.log(error)
      );
  }

/*
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
  }*/

}
