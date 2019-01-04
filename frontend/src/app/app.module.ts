import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';


import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { CustomerinputsComponent } from './customerinputs/customerinputs.component';
import { FileService } from './servers.service';
import { HttpModule } from '@angular/http';
import { AdmincontrollerComponent } from './admincontroller/admincontroller.component';
import { ExtensionsComponent } from './admincontroller/extensions/extensions.component';
import { ServicesComponent } from './admincontroller/services/services.component';
import { ServicefilesComponent } from './admincontroller/servicefiles/servicefiles.component';
import { FiletypesComponent } from './admincontroller/filetypes/filetypes.component';
import { ReportComponent } from './admincontroller/report/report.component';
import { ExtenfilesComponent } from './admincontroller/extenfiles/extenfiles.component';
// import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
// import {MatTableModule} from '@angular/material/table';

const appRoutes: Routes = [
  { path: '', component: CustomerinputsComponent ,
  children:[
    {path:"customerInputs",component:CustomerinputsComponent}
  ]
},
  {
    path: 'adminController', component: AdmincontrollerComponent,
    children:[
      {path:"extensions",component:ExtensionsComponent},
      {path:"services",component:ServicesComponent},
      {path:"filetypes",component:FiletypesComponent},
      {path:"servicefiles",component:ServicefilesComponent},
      {path:"extensionfiles",component:ExtenfilesComponent},
      {path:"report",component:ReportComponent}
    ]
  },
]
@NgModule({
  declarations: [
    AppComponent,
    CustomerinputsComponent,
    AdmincontrollerComponent,
    ExtensionsComponent,
    ServicesComponent,
    ServicefilesComponent,
    FiletypesComponent,
    ReportComponent,
    ExtenfilesComponent   
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [FileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
