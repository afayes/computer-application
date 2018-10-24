import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Computer} from "../../model/computer";
import {ComputerService} from "../../service/computer.service";
import {Router} from "@angular/router";
import {PATH_COMPUTERS} from "../../../settings";


@Component({
  selector: 'computer-detail',
  templateUrl: './computer-detail.component.html',
  styleUrls: ['./computer-detail.component.css']
})
export class ComputerDetailComponent {

  @Input() computer: Computer;
  @Output() deleted = new EventEmitter<Computer>();

  constructor(private computerService: ComputerService,
              private router: Router) {
  }

  deleteComputer() {
    this.computerService.deleteComputer(this.computer.id).subscribe(() => {
        this.deleted.emit(this.computer);
      },
      error => {
        console.error(error)
      })
  }

  editComputer() {
    this.router.navigate([PATH_COMPUTERS + "/" + this.computer.id]);
  }
}
