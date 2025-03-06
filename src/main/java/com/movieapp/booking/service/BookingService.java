package com.movieapp.booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.movieapp.booking.model.MovieBooking;

public interface BookingService {

	ResponseEntity<Map<String, Object>> bookMovie(MovieBooking booking);

	List<MovieBooking> getBookingHistory();
}
