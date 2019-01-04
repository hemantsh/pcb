import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import {Response} from '@angular/http';
@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {
  services=[];
  constructor(private fileService:FileService) { }

  ngOnInit() {
    this.retriveExtn();
  }

  retriveExtn() {

    this.fileService.getServices()
      .subscribe(
        (response: Response) => {
          this.services = response.json();

          console.log("Data is fetching...", this.services);
        },
        (error) => console.log(error)
      );
  }


  updateService(service) {
    if (service.edit || service.add ) {
      console.log(service.edit);
      this.fileService.updateServices(service)
        .subscribe(
          (response: Response) => {
            console.log("Your data is updated", response);
          },
          (error) => console.log(error)
        );

    }
  }

  addService() {
    this.services.unshift({ id: this.services.length + 1, name: null, add: true });
  }

  cancelClick(extn) {
    if (extn.add) {
      this.services.shift();
    } else {
      extn.edit = false;
      extn.name = extn.originalData
    }


  }

}
