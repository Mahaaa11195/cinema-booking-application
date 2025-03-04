package com.movieapp.booking.repository;


import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.movieapp.booking.model.MovieModel;

public interface MovieRepository extends MongoRepository<MovieModel, String>{
	 Optional<MovieModel> findById(String id);
	 Optional<MovieModel> findByMovieTitle(String movieTitle);  // Correct method declaration

	    List<MovieModel> findByGenre(String genre);
	    List<MovieModel> findByDate(String date);
	    List<MovieModel> findByMovieTitleContainingIgnoreCase(String movieTitle);
//	    List<MovieModel> findByLocationContainingIgnoreCase(String location);
	
}
