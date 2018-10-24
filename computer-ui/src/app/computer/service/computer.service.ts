import {Injectable} from "@angular/core";
import {Computer} from "../model/computer";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/internal/Observable";
import {SERVICE_URL} from "../../settings";

// todo put setting in config file/config service

@Injectable()
export class ComputerService {

  constructor(private http: HttpClient) {
  }

  saveComputer(computer: Computer): Observable<Computer> {
    if (computer.id == null) {
      return this.createComputer(computer);
    }
    return this.updateComputer(computer);
  }

  getComputer(id: string): Observable<Computer> {
    return this.http.get<Computer>(SERVICE_URL + "/" + id);
  }

  deleteComputer(id: string): Observable<Computer> {
    return this.http.delete<Computer>(SERVICE_URL + "/" + id)
  }

  getComputers(): Observable<Computer[]> {
    return this.http.get<Computer[]>(SERVICE_URL)
  }

  private createComputer(computer: Computer): Observable<Computer> {
    return this.http.post<Computer>(SERVICE_URL, computer)
  }

  private updateComputer(computer: Computer): Observable<Computer> {
    return this.http.patch<Computer>(SERVICE_URL + "/" + computer.id, computer)
  }
}
