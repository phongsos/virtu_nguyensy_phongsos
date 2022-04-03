import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  imageSearch: string = "";

  searchImage() {
    alert("searchImage()");
    // localhost:8080/current
  }
}
