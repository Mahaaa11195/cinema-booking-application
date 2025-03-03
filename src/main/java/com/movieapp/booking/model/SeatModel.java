package com.movieapp.booking.model;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class SeatModel {
	@Field("seat_number")
    private String seatNumber;
	@Field("price")
    private int price;
	@Field("is_booked")
    private boolean isBooked = false;

    public SeatModel(String seatNumber, int price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }
}

