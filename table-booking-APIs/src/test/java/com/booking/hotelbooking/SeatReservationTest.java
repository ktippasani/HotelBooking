package com.booking.hotelbooking;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.booking.HotelbookingApplication;
import com.booking.model.SeatBooking;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelbookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeatReservationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllSeatBookings() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/seatBookings",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetSeatBookingById() {
		SeatBooking seatBooking = restTemplate.getForObject(getRootUrl() + "/seat-bookings/1", SeatBooking.class);
		assertNotNull(seatBooking);
	}

	@Test
	public void testCreateSeatBooking() {
		SeatBooking seatBooking = new SeatBooking();
		seatBooking.setEmail("admin@gmail.com");
		seatBooking.setName("kotii");
		seatBooking.setTime("11 AM");;

		ResponseEntity<SeatBooking> postResponse = restTemplate.postForEntity(getRootUrl() + "/seat-bookings", seatBooking, SeatBooking.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateSeatBooking() {
		int id = 1;
		SeatBooking seatBooking = restTemplate.getForObject(getRootUrl() + "/seat-bookings/" + id, SeatBooking.class);
		seatBooking.setEmail("admin@gmail.com");
		seatBooking.setName("kotii");
		seatBooking.setTime("11 AM");;

		restTemplate.put(getRootUrl() + "/seatBookings/" + id, seatBooking);

		SeatBooking updatedSeatBooking = restTemplate.getForObject(getRootUrl() + "/seat-bookings/" + id, SeatBooking.class);
		assertNotNull(updatedSeatBooking);
	}

	@Test
	public void testDeleteSeatBooking() {
		int id = 2;
		SeatBooking seatBooking = restTemplate.getForObject(getRootUrl() + "/seat-bookings/" + id, SeatBooking.class);
		assertNotNull(seatBooking);

		restTemplate.delete(getRootUrl() + "/seat-bookings/" + id);

		try {
			seatBooking = restTemplate.getForObject(getRootUrl() + "/seat-bookings/" + id, SeatBooking.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
