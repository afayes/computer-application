import {Component, OnInit} from "@angular/core";
import {Computer} from "../../model/computer";
import {ComputerService} from "../../service/computer.service";
import {Router} from "@angular/router";
import {PATH_COMPUTERS_ADD} from "../../../settings";

@Component({
  selector: 'computers-listing',
  templateUrl: './computer-listing.component.html',
  styleUrls: ['./computer-listing.component.css']
})
export class ComputerListingComponent implements OnInit {

  computers: Computer[] = [];

  constructor(private computerService: ComputerService,
              private router: Router) {
  }

  ngOnInit() {
    this.getComputers();
  }

  goToAddComputerPage() {
    this.router.navigate([PATH_COMPUTERS_ADD]);
  }

  onComputerDelete(computer: Computer) {
    this.getComputers();
  }

  private getComputers() {
    this.computerService.getComputers().subscribe((computers: Computer[]) => {
      this.computers = computers
    })
  }
}
