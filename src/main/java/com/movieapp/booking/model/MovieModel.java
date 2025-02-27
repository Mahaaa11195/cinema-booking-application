package com.movieapp.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movies")
public class MovieModel {

	@Id
	private String id;

	@Field("movie_title")
//	@JsonProperty("movie_title")
	private String movieTitle;

	@Field("genre")
	private String genre;

	@Field("date")
	private String date;

	@Field("location")
	private String location;
}
