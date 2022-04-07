import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  imageSearch: string = "";
  url_backend = 'http://localhost:31001';
  outputs: Array<String> = [];

  constructor(private http: HttpClient) { }

  searchImage() {
    this.http.get(this.url_backend + "/current?location=" + this.imageSearch, {responseType: 'text'}).subscribe(data => {
      this.outputs.push(data);
    })
  }
}
