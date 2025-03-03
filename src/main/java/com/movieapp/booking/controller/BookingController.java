package com.movieapp.booking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.booking.model.MovieBooking;
import com.movieapp.booking.service.BookingService;

@RestController
@RequestMapping("/movie")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<ResponseEntity<Map<String, Object>>> bookMovie(
            @RequestBody MovieBooking booking) {

        ResponseEntity<Map<String, Object>> response = bookingService.bookMovie(booking);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/booking/history")
    public List<MovieBooking> getBookingHistory(){
    	
    	return bookingService.getBookingHistory();
    }
}

