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
  constructor(private fileService:FileService) { }
  ngOnInit() {
    this.retriveExtn();
  }
  
  retriveExtn(){
  
    this.fileService.getExtensions()
    .subscribe(
      (response:Response)=>{
        this.extns=response.json();
        console.log("data",  this.extns);
      },
      (error)=> console.log(error)
    );
  }

}
