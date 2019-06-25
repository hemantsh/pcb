import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'app-help-modal',
  templateUrl: './help-modal.component.html',
  styleUrls: ['./help-modal.component.css']
})
export class HelpModalComponent implements OnInit {
   @Input() helpData:{title:string,instructions:Array<string>};
  constructor() { }

  ngOnInit() {
  }

}
