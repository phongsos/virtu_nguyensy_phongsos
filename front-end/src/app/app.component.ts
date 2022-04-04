import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'front-end';
  imageSearch: string = "";
  url_backend = 'http://microserv3-service.default.svc.cluster.local:80';

  constructor(private http: HttpClient) { }

  searchImage() {
    this.http.get<any>(this.url_backend + "/").subscribe(data => {
      alert(data.text);
    })
  }
}
