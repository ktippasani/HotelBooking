import {NgModule, Component,OnInit } from '@angular/core';
import { Booking } from '../booking';
import { Router,ActivatedRoute } from '@angular/router';
import { BookingService } from '../booking-service.service';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {
  
  id: number = 0;
  booking: Booking = new Booking();
  constructor(private route: ActivatedRoute, private router: Router,
    private bookingService: BookingService) {
     }
    ngOnInit() {
      this.booking = new Booking();
      //Get the Id from request URL and make the GET call to get the booking Info to display.
      this.id = this.route.snapshot.params['id'];
      console.log("id======================"+this.id)
      this.bookingService.getBooking(this.id)
        .subscribe(data => {
          console.log(data)
          this.booking = data;
        }, error => console.log(error));
    }
}
