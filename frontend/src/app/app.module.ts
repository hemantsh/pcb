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
import { CanDeactivateGuard } from './admincontroller/extenfiles/candeactivate-guard.service';
import {CanServiceFilesDeactivateGuard} from './admincontroller/servicefiles/candeactivate-servicefilesguard.service';

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
      {path:"servicefiles",component:ServicefilesComponent,canDeactivate:[CanServiceFilesDeactivateGuard]},
      {path:"extensionfiles",component:ExtenfilesComponent,canDeactivate:[CanDeactivateGuard]},
      {path:"report",component:ReportComponent}
    ]
  }
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
  providers: [FileService,CanDeactivateGuard,CanServiceFilesDeactivateGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
