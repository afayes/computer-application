import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ComputerListingComponent} from "./computer/component/computer-listing/computer-listing.component";
import {AddComputerComponent} from "./computer/component/add-computer/add-computer.component";
import {PATH_COMPUTER, PATH_COMPUTERS_ADD, PATH_COMPUTERS_LISTING} from "./settings";

const routes: Routes = [
  {path: '', redirectTo: PATH_COMPUTERS_LISTING, pathMatch: 'full'},
  {path: PATH_COMPUTERS_LISTING, component: ComputerListingComponent},
  {path: PATH_COMPUTERS_ADD, component: AddComputerComponent},
  {path: PATH_COMPUTER, component: AddComputerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
