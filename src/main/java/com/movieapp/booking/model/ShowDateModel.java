package com.movieapp.booking.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDateModel {

	@Field("date")
	private LocalDateTime date;

	@Field("shows")
	private List<ShowModel> shows;
}
