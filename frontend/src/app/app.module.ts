import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { CustomerinputsComponent } from './customerinputs/customerinputs.component';
import { FileUploadService } from './servers.service';
import { HttpModule } from '@angular/http';


@NgModule({
  declarations: [
    AppComponent,
    CustomerinputsComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [FileUploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
