import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';

export interface ForecastDay {
  date: string,
  maxTemperature: number,
  minTemperature: number,
  humidity: number,
  text: string
}

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
  autocompletions: string[] = [];
  forecasts: ForecastDay[] = [];

  // Current
  current_temp = "null";
  current_loc1 = "null";
  current_loc2 = "null";
  current_humidity = "null";
  current_wind = "null";
  current_text = "null";

  // Advices
  objects_adv: string[] = [];
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
    this.getAdvices();
    this.getForecasts();
  }

  getWeather() {
    this.http.get(this.url_ms1 + "/current?location=" + this.location, {responseType: 'text'}).subscribe(data => {
      this.outputs.push(data);
      const json = JSON.parse(data);
      this.current_temp = json.temperature;
      this.current_loc1 = json.location.split(',')[0];
      this.current_loc2 = json.location.split(',')[1] + " (" + json.location.split(',')[2] + " )";
      this.current_wind = json.wind;
      this.current_humidity = json.humidity;
      this.current_text = json.text;
    })
  }

  getAdvices() {
    this.http.get(this.url_ms3 + "/current?location=" + this.location, {responseType: 'text'}).subscribe(data => {
      this.objects_adv = [];
      const arr = JSON.parse(data);
      arr.forEach((url: string) => {
        this.objects_adv.push(url);
      });
    })
  }

  getAutocompletion() {
    this.http.get(this.url_ms1 + "/autocomplete?query=" + this.location, {responseType: 'text'}).subscribe(data => {
      const json = JSON.parse(data);
      this.autocompletions = json;
    })
  }

  getForecasts() {
    this.http.get(this.url_ms1 + "/forecasts?location=" + this.location, {responseType: 'text'}).subscribe(data => {
      const json = JSON.parse(data);
      this.forecasts = json;
    })
  }
}
