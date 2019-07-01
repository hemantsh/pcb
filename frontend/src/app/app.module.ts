import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';

import { FileService } from './servers.service';
import { HttpModule } from '@angular/http';

import { AdmincontrollerComponent } from './admincontroller/admincontroller.component';
// <------------------------------  Components   ---------------------------------------------> 
import { ServicesComponent } from './admincontroller/services/services.component';
import { ServicefiletypesComponent } from './admincontroller/servicefiletypes/servicefiletypes.component';
import { FiletypeextensionsComponent } from './admincontroller/filetypeextensions/filetypeextensions.component';
import { ReportComponent } from './admincontroller/report/report.component';


// <------------------------------  Component Guards   ---------------------------------------> 
import { CanServiceDeactivateGuard } from './admincontroller/services/candeactivate-service.service';
import { CanFiletypeExtnDeactivateGuard } from './admincontroller/filetypeextensions/can-deactivate-filetype-extn.service';
import { CanServiceFiletypesDeactivateGuard } from './admincontroller/servicefiletypes/candeactivate.servicefiletypes.service';
// <-------------------------- Wrong URL Handlers ------------------------------------------->
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HelpModalComponent } from './help-modal/help-modal.component';

/**
 * Routes are used to navigate from one page to another.
 */
const appRoutes: Routes = [
  {

    path: '', component: AdmincontrollerComponent,
    children: [
      { path: "services", component: ServicesComponent, canDeactivate: [CanServiceDeactivateGuard] },
      { path: "servicefiletypes", component: ServicefiletypesComponent, canDeactivate: [CanServiceFiletypesDeactivateGuard] },
      { path: "filetypeextensions", component: FiletypeextensionsComponent, canDeactivate: [CanFiletypeExtnDeactivateGuard] },
      { path: "report", component: ReportComponent },
    ]
  },
  { path: 'page-not-found', component: PageNotFoundComponent },
  { path: '**', redirectTo: 'page-not-found' }
]

@NgModule({
  declarations: [
    AppComponent,
    AdmincontrollerComponent,
    ServicesComponent,
    ReportComponent,
    PageNotFoundComponent,
    FiletypeextensionsComponent,
    ServicefiletypesComponent,
    HelpModalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [FileService, CanServiceDeactivateGuard, CanFiletypeExtnDeactivateGuard, CanServiceFiletypesDeactivateGuard],

  bootstrap: [AppComponent]
})
export class AppModule { }
