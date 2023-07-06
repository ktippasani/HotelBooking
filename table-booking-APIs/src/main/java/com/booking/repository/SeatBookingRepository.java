package com.booking.repository;

import java.time.LocalDate;
import java.util.List;
import com.booking.model.SeatBooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {
    //List<SeatBooking> findByBookingDate(LocalDate date);
}