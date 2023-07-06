package com.booking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.model.SeatBooking;
import com.booking.repository.SeatBookingRepository;

@Service
public class SeatBookingService {
	@Autowired
	private SeatBookingRepository seatBookingRepository;


	public List<SeatBooking> getSeatBookingsByDate(LocalDate date) {
		return null;
		//seatBookingRepository.findByBookingDate(date);
	}

	public SeatBooking saveSeatBooking(SeatBooking seatBooking) {
		// Additional validation and business logic if needed
		return seatBookingRepository.save(seatBooking);
	}

	public SeatBooking getBookingById(Long id) {
		System.out.println("Booking Id== "+id);
		// Additional validation and business logic if needed
		return seatBookingRepository.findById(id).get();
	}
	public SeatBooking updateBooking(Long id, SeatBooking seatBookingNew) {
		// Additional validation and business logic if needed
		SeatBooking seatBooking = seatBookingRepository.getById(id);
		seatBooking.setDate(seatBookingNew.getDate());
		seatBooking.setSeatCount(seatBookingNew.getSeatCount());

		return seatBookingRepository.save(seatBooking);
	}
	
	public void deleteBooking(Long id) {
		// Additional validation and business logic if needed
		seatBookingRepository.deleteById(id);
	}

	// Additional methods for updating, deleting seat bookings, etc.
}
