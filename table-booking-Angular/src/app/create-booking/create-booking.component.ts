import { NgModule ,Component, OnInit } from '@angular/core';
import { BookingService } from '../booking-service.service';
import { Booking } from '../booking';
import { Router } from '@angular/router';
import { DateAdapter } from '@angular/material/core';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent implements OnInit{

  
  booking: Booking = new Booking();
  submitted = false;

  minDate: Date = new Date();
  
  constructor(private bookingService: BookingService,
    private router: Router, private dateAdapter: DateAdapter<Date>) {
      this.minDate = this.dateAdapter.today();
     }

  ngOnInit() {
  }

  newBooking(): void {
    this.submitted = false;
    this.booking = new Booking();
  }

  save() {
    this.booking.date = new Date(this.booking.date).toISOString();
    this.bookingService
    .createBooking(this.booking).subscribe(data => {
      this.booking = new Booking();
      console.log(data)
      this.gotoList(data);
    }, 
    error => console.log(error));
  }

  onSubmit(f: NgForm) {
    //Stop here if form is invalid
    if (f.invalid) {
      return;
  }
  alert('SUCCESS!! :-)\n\n' + JSON.stringify(f.value, null, 4));
    this.save();    
  }

  gotoList(data : any) {
    this.router.navigate(['bookings', data.id]);
  }

}
