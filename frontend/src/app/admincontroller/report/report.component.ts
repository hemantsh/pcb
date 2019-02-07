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
  //demoProjectJSON={"projectDetail":{"projectId":"1234","customerInformation":{"projectId":"1234"},"boardInfo":{"serviceType":"Assembly","layers":0,"itar":false},"fileDetails":[{"name":"standard","format":"unknown","valid":false,"context":"board","type":"silk_screen","polarity":"positive","side":"top","layerOrder":0,"layerName":"silk_screen-top"},{"name":"components","format":"unknown","valid":false,"layerOrder":0},{"name":"8000-4890.zip","format":"unknown","valid":false,"context":"misc","type":"other","polarity":"none","side":"none","layerOrder":0,"layerName":"other"},{"name":"data","format":"unknown","valid":false,"layerOrder":0},{"name":"stephdr","format":"unknown","valid":false,"context":"board","type":"silk_screen","polarity":"positive","side":"top","layerOrder":0,"layerName":"silk_screen-top"},{"name":"8000-4890CPWIZA.GBL","format":"gerber","valid":false,"context":"board","type":"signal","polarity":"positive","side":"bottom","layerOrder":0,"layerName":"copper-bot"},{"name":"8000-4890CPWIZA.GBO","format":"gerber","valid":false,"context":"board","type":"silk_screen","polarity":"positive","side":"bottom","layerOrder":0,"layerName":"silk_screen-bot"},{"name":"matrix","format":"unknown","valid":false,"context":"misc","type":"bom","polarity":"none","side":"none","layerOrder":0,"layerName":"assembly"},{"name":"8000-4890CPWIZA.GBP","format":"unknown","valid":false,"context":"board","type":"solder_paste","polarity":"positive","side":"bottom","layerOrder":0,"layerName":"solder_paste-bot"},{"name":"tools","format":"unknown","valid":false,"layerOrder":0},{"name":"features","format":"unknown","valid":false,"layerOrder":0},{"name":"8000-4891 REV A PCB, CONTROL PANEL (FAB).pdf","format":"gerber","valid":false,"context":"misc","type":"drawing","polarity":"positive","side":"top","layerOrder":0,"layerName":"fab"},{"name":"8000-4890CPWIZA.REP","format":"unknown","valid":false,"context":"misc","type":"document","polarity":"none","side":"none","layerOrder":0,"layerName":"reference"},{"name":"8000-4891CPWIZA_ARTWORK.PDF","format":"gerber","valid":false,"layerOrder":0},{"name":"8000-4890CPWIZA-SquareHoles.TXT","format":"gerber","valid":false,"context":"board","type":"drill","polarity":"positive","side":"top","layerOrder":0,"layerName":"drill"},{"name":"8000-4890CPWIZA.GTL","format":"gerber","valid":false,"context":"board","type":"signal","polarity":"positive","side":"top","layerOrder":0,"layerName":"copper-top"},{"name":"8000-4890CPWIZA.GTO","format":"gerber","valid":false,"context":"board","type":"silk_screen","polarity":"positive","side":"top","layerOrder":0,"layerName":"silk_screen-top"},{"name":"8000-4890CPWIZA.GTP","format":"unknown","valid":false,"context":"board","type":"signal","polarity":"positive","side":"top","layerOrder":0,"layerName":"copper-top"},{"name":"8000-4890 REV A PCA, CONTROL PANEL (ASSY).pdf","format":"gerber","valid":false,"context":"misc","type":"drawing","polarity":"positive","side":"top","layerOrder":0,"layerName":"assembly"},{"name":"8000-4890CPWIZA.DRR","format":"unknown","valid":false,"context":"board","type":"drill","polarity":"positive","side":"top","layerOrder":0,"layerName":"drill"},{"name":"8000-4890CPWIZA.EXTREP","format":"unknown","valid":false,"context":"misc","type":"document","polarity":"none","side":"none","layerOrder":0,"layerName":"reference"},{"name":"8000-4890CPWIZA_RoHS.xlsx","format":"unknown","valid":false,"context":"misc","type":"bom","polarity":"none","side":"none","layerOrder":0,"layerName":"assembly"},{"name":"8000-4890CPWIZA.GTS","format":"gerber","valid":false,"context":"board","type":"solder_mask","polarity":"positive","side":"top","layerOrder":0,"layerName":"solder_mask-top"},{"name":"attrlist","format":"unknown","valid":false,"context":"board","type":"silk_screen","polarity":"positive","side":"top","layerOrder":0,"layerName":"silk_screen-top"},{"name":"8000-4890CPWIZA.GP1","format":"gerber","valid":false,"context":"board","type":"power_ground","polarity":"negative","side":"none","layerOrder":1,"layerName":"copper-\\1n"},{"name":"8000-4890CPWIZA.RUL","format":"unknown","valid":false,"layerOrder":0},{"name":"ax8604a_8000-4890cpwiza_netlist.rep","format":"unknown","valid":false,"context":"misc","type":"document","polarity":"none","side":"none","layerOrder":0,"layerName":"reference"},{"name":"8000-4890CPWIZA.GP2","format":"gerber","valid":false,"context":"board","type":"power_ground","polarity":"negative","side":"none","layerOrder":1,"layerName":"copper-\\1n"},{"name":"netlist","format":"unknown","valid":false,"context":"board","type":"silk_screen","polarity":"positive","side":"top","layerOrder":0,"layerName":"silk_screen-top"},{"name":"profile","format":"unknown","valid":false,"context":"misc","type":"document","polarity":"positive","side":"none","layerOrder":0,"layerName":"outline"},{"name":"8000-4890CPWIZA.txt","format":"gerber","valid":false,"context":"board","type":"drill","polarity":"positive","side":"top","layerOrder":0,"layerName":"drill"},{"name":"8000-4890CPWIZA-macro.APR_LIB","format":"unknown","valid":false,"layerOrder":0},{"name":"8000-4890CPWIZA-RoundHoles.TXT","format":"gerber","valid":false,"context":"board","type":"drill","polarity":"positive","side":"top","layerOrder":0,"layerName":"drill"},{"name":"8000-4892 REV A SCH. CONTROL PANEL.PDF","format":"gerber","valid":false,"layerOrder":0},{"name":"8000-4890CPWIZA.GBS","format":"gerber","valid":false,"context":"board","type":"solder_mask","polarity":"positive","side":"bottom","layerOrder":0,"layerName":"solder_mask-bot"},{"name":"8000-4890CPWIZA.LDP","format":"unknown","valid":false,"layerOrder":0},{"name":"8000-4890CPWIZA.ipc","format":"unknown","valid":false,"context":"misc","type":"document","polarity":"none","side":"none","layerOrder":0,"layerName":"reference"},{"name":"8000-4890CPWIZA-RectHoles.TXT","format":"gerber","valid":false,"context":"board","type":"drill","polarity":"positive","side":"top","layerOrder":0,"layerName":"drill"},{"name":"8000-4890CPWIZA.apr","format":"unknown","valid":false,"layerOrder":0},{"name":"8000-4890CPWIZA.tgz","format":"unknown","valid":false,"layerOrder":0}],"fileNames":["standard","components","8000-4890.zip","data","stephdr","8000-4890CPWIZA.GBL","8000-4890CPWIZA.GBO","matrix","8000-4890CPWIZA.GBP","tools","features","8000-4891 REV A PCB, CONTROL PANEL (FAB).pdf","8000-4890CPWIZA.REP","8000-4891CPWIZA_ARTWORK.PDF","8000-4890CPWIZA-SquareHoles.TXT","8000-4890CPWIZA.GTL","8000-4890CPWIZA.GTO","8000-4890CPWIZA.GTP","8000-4890 REV A PCA, CONTROL PANEL (ASSY).pdf","8000-4890CPWIZA.DRR","8000-4890CPWIZA.EXTREP","8000-4890CPWIZA_RoHS.xlsx","8000-4890CPWIZA.GTS","attrlist","8000-4890CPWIZA.GP1","8000-4890CPWIZA.RUL","ax8604a_8000-4890cpwiza_netlist.rep","8000-4890CPWIZA.GP2","netlist","profile","8000-4890CPWIZA.txt","8000-4890CPWIZA-macro.APR_LIB","8000-4890CPWIZA-RoundHoles.TXT","8000-4892 REV A SCH. CONTROL PANEL.PDF","8000-4890CPWIZA.GBS","8000-4890CPWIZA.LDP","8000-4890CPWIZA.ipc","8000-4890CPWIZA-RectHoles.TXT","8000-4890CPWIZA.apr","8000-4890CPWIZA.tgz"]},"summary":"****** File upload and basic validation by name and extension. *******","validationStatus":"Matched with all required file types. All information collected."};
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
    this.fileService.getReport()
      .subscribe(
        (response: Response) => {
          this.reports = response.json();
          this.versionStyle='show';
          console.log("Data is fetching...", this.reports);
        },
        (error) => console.log(error)
      );
  }
  /*retriveFiletype(fileType) {

    this.fileService.getFiletype(fileType)
      .subscribe(
        (response: Response) => {
          this.fileType = response.json();

          console.log("Data is fetching...", this.fileType);
        },
        (error) => console.log(error)
      );
  }
*/
onVersionSelect(selectedVersionId){
  this.btnDisable=false;
   
}
  onProjectIdSelect(selectedReportId){
    // this.fileService.getReportById(selectedReportId)
    // .subscribe(
    //   (response: Response) => {
    //     this.selectedReport = response.json();

    //     console.log("Selected Report is fetching...", this.selectedReport);
    //   },
    //   (error) => console.log(error)
    // );
    this.divStyle='show';
  }

}
