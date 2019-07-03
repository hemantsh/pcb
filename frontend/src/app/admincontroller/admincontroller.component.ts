import { Component, OnInit, ElementRef, ViewChild, HostListener, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-admincontroller',
  templateUrl: './admincontroller.component.html',
  styleUrls: ['./admincontroller.component.css']
})
export class AdmincontrollerComponent implements OnInit, AfterViewInit {
  scrHeight: any;
  scrWidth: any;
  @ViewChild("ref") ref: ElementRef;

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    this.scrHeight = window.innerHeight;
    this.scrWidth = window.innerWidth;
    console.log(this.scrHeight, this.scrWidth);
    if (this.scrWidth >= 767) {
      this.ref.nativeElement.style.width = "100%";
    }
  }

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    this.getScreenSize();
  }
  openMenu() {
    this.ref.nativeElement.style.width = "250px";

  }

  closeMenu() {
    if (this.scrWidth <= 767)
      this.ref.nativeElement.style.width = "0";
  }
}
