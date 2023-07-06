import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private baseUrl = 'http://localhost:8080/api/seat-bookings';

  constructor(private http: HttpClient) { }

  createBooking(booking: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, booking);
  }

  getBooking(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}
