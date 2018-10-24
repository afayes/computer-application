import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {ComputerListingComponent} from "./computer/component/computer-listing/computer-listing.component";
import {ComputerService} from "./computer/service/computer.service";
import {AppRoutingModule} from "./app-routing.module";
import {AddComputerComponent} from "./computer/component/add-computer/add-computer.component";
import {FormsModule} from "@angular/forms";
import {ComputerDetailComponent} from "./computer/component/computer-detail/computer-detail.component";

@NgModule({
  declarations: [
    AppComponent,
    ComputerListingComponent,
    AddComputerComponent,
    ComputerDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [ComputerService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
