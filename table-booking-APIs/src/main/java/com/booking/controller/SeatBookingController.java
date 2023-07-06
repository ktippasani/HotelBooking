package com.booking.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.model.SeatBooking;
import com.booking.service.SeatBookingService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SeatBookingController {

	@Autowired
	private SeatBookingService seatBookingService;

	@PostMapping("/seat-bookings")
	public SeatBooking saveSeatBooking(@RequestBody SeatBooking seatBooking) {
		return seatBookingService.saveSeatBooking(seatBooking);
	}

	@GetMapping("/seat-bookings/{id}")
	public ResponseEntity<SeatBooking>  getBookingById(@PathVariable(value = "id") Long id) {

		SeatBooking seatBooking = seatBookingService.getBookingById(id);
		return ResponseEntity.ok().body(seatBooking);
	}

	@PutMapping("/seat-bookings/{id}")
	public SeatBooking updateBooking(@PathVariable(value = "id") Long id,  @RequestBody SeatBooking seatBooking ) {
		return seatBookingService.updateBooking(id, seatBooking);
	}

	@DeleteMapping("/seat-bookings/{id}")
	public void deleteBooking(@PathVariable(value = "id") Long id) {
		seatBookingService.deleteBooking(id);
	}

	@GetMapping("/seat-bookings")
	public List<SeatBooking> getSeatBookingsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return seatBookingService.getSeatBookingsByDate(date);
	}




	// Additional API endpoints for updating, deleting seat bookings, etc.
}
