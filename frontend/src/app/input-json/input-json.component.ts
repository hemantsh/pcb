import { Component, OnInit } from '@angular/core';
import { FileService } from '../servers.service';

@Component({
  selector: 'app-input-json',
  templateUrl: './input-json.component.html',
  styleUrls: ['./input-json.component.css']
})
export class InputJSONComponent implements OnInit {

  constructor(private fileService: FileService) { }

  inputJSON;
  ngOnInit() {
  }

  /**
   * Saves the data into database.
   */
  onSend() {
    console.log(JSON.parse(this.inputJSON));

    this.fileService.saveFileManagementReport(JSON.parse(this.inputJSON))
      .subscribe(
        (response) => {
          if (response.status == 200) {
            console.log(response);
            alert('Your data has been saved successfully.');
          }
          else {
            alert('Some error has been encountered'); 
          }
        },
        (error) => {
          alert(error);
          console.log(error)
        }
      );
  }
}
