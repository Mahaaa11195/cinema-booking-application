package com.movieapp.booking.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieBooking {
//    @Id
//    private String id;
//    @Field("movie_id")
//    private String movieId;
//    @Field("tickets")
//    private int tickets;
//    @Field("payment_status")
//    private String paymentStatus;

	@Id
	private String id;
	@Field("movie_id")
	private String movieId;
	@Field("date")
	private String date;
	@Field("time")
	private String time;
	@Field("location")
	private String location;
	@Field("booked_seats")
//    private List<String> bookedSeats;
    private List<String> bookedSeats = new ArrayList<>(); // Ensure it's initialized
	@Field("payment_status")
	private String paymentStatus;
	@Field("total_price")
	private int totalPrice;
	 // Constructors, Getters, and Setters
    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<String> bookedSeats) {
        if (bookedSeats == null) {
            this.bookedSeats = new ArrayList<>(); // Avoid null values
        } else {
            this.bookedSeats = bookedSeats;
        }
    }
}
