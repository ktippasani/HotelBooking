import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
//Service class to make REST API calls.
export class BookingService {
  username ='koti';
  password='password';
  
  //Spring Boot REST APIs URL
  private baseUrl = 'http://localhost:8080/api/seat-bookings';

  constructor(private http: HttpClient) { }
//Option 1-  Create header with username and password
   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa(this.username + ":" + this.password)
    })
  };
  //Option 2-  Create header with username and password
  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ':' + password)
  }
  //POST call to REST API with basic auth
  createBooking(booking: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` , booking, this.httpOptions);
  }
  //GET call to REST API with basic auth
  getBooking(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, { headers: { authorization: this.createBasicAuthToken("koti", "password") } } );
  }
}
