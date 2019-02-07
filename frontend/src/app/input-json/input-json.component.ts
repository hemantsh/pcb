import { Component, OnInit } from '@angular/core';
import {FileService} from '../servers.service';

@Component({
  selector: 'app-input-json',
  templateUrl: './input-json.component.html',
  styleUrls: ['./input-json.component.css']
})
export class InputJSONComponent implements OnInit {

  constructor(private fileService:FileService) { }

  inputJSON;
  ngOnInit() {
  }

  onSend(){
    //console.log(JSON.parse(this.inputJSON));
    this.fileService.testApi(this.inputJSON)
    .subscribe(
      (response)=>{
        console.log(response);
      },
      (error) =>console.log(error)
    );
  }
}
