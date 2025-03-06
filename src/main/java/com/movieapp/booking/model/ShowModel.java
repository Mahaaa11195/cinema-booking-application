package com.movieapp.booking.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
public class ShowModel {

	@Field("time")
	private String time; // e.g., "9 AM", "12 PM", "6 PM"

	@Field("total_seats")
	private int totalSeats = 5; // Assume 5 seats per show

	@Field("available_seats")
	private List<SeatModel> availableSeats;

	public ShowModel(String time) {
		this.time = time;
		this.availableSeats = List.of(new SeatModel("A1", 200), new SeatModel("A2", 200), new SeatModel("A3", 150),
				new SeatModel("A4", 150), new SeatModel("A5", 150));
	}
}