import {Component, Input, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {Computer} from "../../model/computer";
import {ComputerService} from "../../service/computer.service";
import {PATH_COMPUTERS_LISTING} from "../../../settings";

@Component({
  selector: 'add-computer',
  templateUrl: './add-computer.component.html',
  styleUrls: ['./add-computer.component.css']
})
export class AddComputerComponent implements OnInit {

  @Input() computer: Computer = new Computer();

  constructor(private computerService: ComputerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    if (this.route.snapshot.paramMap.has("id")) {
      const id = this.route.snapshot.paramMap.get('id');
      this.computerService.getComputer(id).subscribe((computer: Computer) => {
        this.computer = computer;
        this.computer.dateAddedText = Computer.formatDate(computer.dateAdded);
      })
    } else {
      this.computer = new Computer()
    }
  }

  save() {
    this.computer.dateAdded = Computer.parseDate(this.computer.dateAddedText);
    this.computerService.saveComputer(this.computer).subscribe(() => {
      this.router.navigate([PATH_COMPUTERS_LISTING]);
    }, error => {
      console.error(error)
    });
  }

  cancel() {
    this.router.navigate([PATH_COMPUTERS_LISTING]);
  }
}
