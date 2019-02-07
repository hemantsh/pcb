import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
 import {Response} from '@angular/http';
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  reports={};
  fileType=[];

  ProjectId=[];
  Version=[];
  selectedProjectId=0;
  selectedReport=[];
  selectedVersionId=0;
  selectedVersion=[];
  
  divStyle='hide';
  versionStyle='hide';
  btnDisable=true;
  Object = Object;
  constructor(private fileService:FileService) { }

  ngOnInit() {
    this.retriveProjectId();
    this.retriveVersion();
  }
  
  retriveProjectId() {

    this.fileService.getUniqueId()
      .subscribe(
        (response: Response) =>{
          this.ProjectId=response.json();
          console.log("ProjectId....", this.ProjectId);
        },
        (error) => console.log(error)
      );
  }
  retriveVersion(){
    this.fileService.getReport()
    .subscribe(
      (response: Response) =>{
        this.Version=response.json();
        console.log("Version is fetching...", this.Version);
      },
      (error) => console.log(error)
    );

  }
  onShow() {
    this.fileService.getReportByIdAndVersion(this.selectedProjectId,this.selectedVersionId)
      .subscribe(
        (response: Response) => {
          this.reports = response.json();
          this.versionStyle='show';
          console.log("Report is fetching...", this.reports);
        },
        (error) => console.log(error)
      );
  }
  
onVersionSelect(selectedVersionId){
  this.btnDisable=false;
  this.selectedVersionId=selectedVersionId;
  console.log(this.selectedVersionId);
}
  onProjectIdSelect(selectedProjectId){
    this.divStyle='show';
    this.selectedProjectId=selectedProjectId;
    console.log(this.selectedProjectId);
  }

  updateData(data){
    console.log(data);
  }
  editData(data) {
    if (data.edit || data.add ) {
      console.log(data.edit);
      data.edit=true;
    }
  }
  cancelClick(extn) {
    if (extn.add) {
      this.reports;
    } else {
      extn.edit = false;
      extn.name = extn.originalData
    }
  }
}
