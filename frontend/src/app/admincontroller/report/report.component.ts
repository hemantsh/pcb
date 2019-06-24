import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  report: any;
  differences;
  fileType = [];

  projectIds = [];
  version = [];
  selectedProjectId = 0;
  selectedReport = [];
  selectedVersionId = 0;

  divStyle = 'hide';
  versionStyle = 'hide';
  btnDisable = true;
  Object = Object;

  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.retriveProjectId();
  }

  /**
   * retrives the unique project Id from the database.
   */
  retriveProjectId() {
    this.fileService.getUniqueId()
      .subscribe(
        (response: Response) => {
          this.projectIds = response.json();
          console.log("ProjectId with multiple verisons", this.projectIds);
        },
        (error) => console.log(error)
      );
  }
/**
 * Shows the report of the selected projectId and selected versionId.
 */
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
    this.fileService.getDifferences(this.selectedProjectId).subscribe(
      (response: Response) => {
        if(response)
          this.differences = response.json();
        console.log("Differences is fetchin...", this.differences);
      },
      (error) => console.log(error)
    );
  }

  /**
   * Enables the show report button.
   * @param selectedVersionId sets the selectedVersionId
   */
  onVersionSelect(selectedVersionId) {
    this.btnDisable = false;
    this.selectedVersionId = selectedVersionId;
    console.log(this.selectedVersionId);
  }

  /**
   * Enables the VersionId dropdown selection.
   * @param selectedProjectId sets the seletedProjectId
   */
  onProjectIdSelect(selectedProjectId) {
    this.divStyle = 'show';
    this.selectedProjectId = selectedProjectId;
    console.log(this.selectedProjectId);
  }
  /**
   * updateData updates the data that needs to be updated by the user.
   * @param data contatains the report that has updated into the html from user
   */
  updateData(data) {
    data.attachReplace = true;
    this.fileService.saveFileManagementReport(data)
      .subscribe(
        (response: Response) => {
          console.log("data is", data);
          this.versionStyle = 'show';
          console.log("Report is fetching...", response);
        },
        (error) => console.log(error)
      );
    if (this.report.customerIdEdit) {
      delete this.report.customerIdEdit;
      delete this.report.originalCustId;
    }
    if (this.report.emailEdit) {
      delete this.report.emailEdit;
      delete this.report.originalEmail;
    }
  }

  /**
   * Cancels the edit mode into the CustomerId into the Report Page.
   */
  cancelCustId() {
    this.report.customerId = this.report.originalCustId;
    this.report.customerIdEdit = false;
  }
  /**
   * Cancels the edit mode into the Report Page.
   * @param data takes the report into it.
   */
  cancelEmail(data) {
    data.emailAddress = data.originalEmail;
    data.emailEdit = false;
  }
}
