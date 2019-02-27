import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/servers.service';
import { Response } from '@angular/http';
@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.css']
})
export class ServicesComponent implements OnInit {
  services = [];
  constructor(private fileService: FileService) { }

  ngOnInit() {
    this.retriveExtn();
  }

  /**
   * To reterive list of services
   */
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

  /**
   * Updates the already existing service. 
   * @param service takes service object
   */
  updateService(service) {
    if (service.edit || service.add) {
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

  /**
   * Adds a textbox into the list for adding new service. 
   */
  addService() {
    this.services.unshift({ id: this.services.length + 1, name: null, add: true });
  }

  /**
   * Removes the textbox.
   * @param service contains service property
   */
  cancelClick(service) {
    if (service.add) {
      this.services.shift();
    } else {
      service.edit = false;
      service.name = service.originalData
    }
  }

}
