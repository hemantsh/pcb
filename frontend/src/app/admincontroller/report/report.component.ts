import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  report : any;
  fileType = [];

  projectIds = [];
  version = [];
  selectedProjectId = 0;
  selectedReport = [];
  selectedVersionId = 0;
  //selectedVersion=[];

  divStyle = 'hide';
  versionStyle = 'hide';
  btnDisable = true;
  Object = Object;

  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.retriveProjectId();
  }

  retriveProjectId() {

    this.fileService.getUniqueId()
      .subscribe(
        (response: Response) => {
          this.projectIds = response.json();
          console.log("ProjectId....", this.projectIds);
        },
        (error) => console.log(error)
      );
  }

  onShow() {
    this.fileService.getReportByIdAndVersion(this.selectedProjectId, this.selectedVersionId)
      .subscribe(
        (response: Response) => {
          this.report = response.json();
          this.versionStyle = 'show';
          console.log("Report is fetching...", this.report);
        },
        (error) => console.log(error)
      );
  }

  onVersionSelect(selectedVersionId) {
    this.btnDisable = false;
    this.selectedVersionId = selectedVersionId;
    console.log(this.selectedVersionId);
  }
  onProjectIdSelect(selectedProjectId) {
    this.divStyle = 'show';
    this.selectedProjectId = selectedProjectId;
    console.log(this.selectedProjectId);
  }

  updateData(data) {
    data.attachReplace=true;
    this.fileService.saveFileManagementReport(data)
    .subscribe(
      (response: Response) => {
        console.log("data is", data);
        this.versionStyle='show';
        console.log("Report is fetching...", response);
      },
      (error) => console.log(error)
    );
    if (this.report.customerIdEdit) {
      delete this.report.customerIdEdit;
      delete this.report.originalCustId;
    }  
    if(this.report.emailEdit){
      delete this.report.emailEdit;
      delete this.report.originalEmail;
    }
  }

  cancelCustId() {
      this.report.customerId = this.report.originalCustId;
      this.report.customerIdEdit = false;
  }
  
  cancelEmail(data) {
      data.emailAddress = data.originalEmail;
      data.emailEdit = false;
  }
}
