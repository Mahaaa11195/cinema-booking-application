package com.movieapp.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.movieapp.booking.model.MovieModel;

public interface MovieService {
	ResponseEntity<?> save(MovieModel movie);
    List<MovieModel> getAllMovies();
    void deleteMovie(String movieId);
    Optional<MovieModel> updateMovie(String movieId, MovieModel updatedMovie);
    MovieModel getMovieById(String movieId);
    
    List<MovieModel> getMovieByParams(String movieTitle, String genre, String date, String location);
}