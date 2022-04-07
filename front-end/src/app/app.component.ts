import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  location: string = "Paris";
  url_ms1 = 'http://localhost:31002';
  url_ms3 = 'http://localhost:31001';
  outputs: Array<String> = [];

  // Current
  current_temp = "";
  current_loc1 = "";
  current_loc2 = "";

  date = new Date();

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.search();
  }

  search() {
    /*this.http.get(this.url_ms3 + "/current?location=" + this.location, {responseType: 'text'}).subscribe(data => {
      this.outputs.push(data);
    })*/
    this.getWeather();
  }

  getWeather() {
    this.http.get(this.url_ms1 + "/current?location=" + this.location, {responseType: 'text'}).subscribe(data => {
      this.outputs.push(data);
    })
  }

}
