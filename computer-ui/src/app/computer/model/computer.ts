import * as moment from 'moment';
import {DATE_FORMAT} from "../../settings";


// todo put setting in config file/config service

export class Computer {
  id: string;
  name: string;
  ip: string;
  mac: string;
  location: string;
  dateAdded: Date;
  dateAddedText: string;

  static formatDate(date: Date): string {
    return moment(date).format(DATE_FORMAT)
  }

  static parseDate(date: string): Date {
    return moment(date, DATE_FORMAT).toDate();
  }
}
