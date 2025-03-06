package com.movieapp.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.booking.model.MovieModel;
import com.movieapp.booking.service.MovieService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/save")
	public ResponseEntity<?> addMovie(@RequestBody MovieModel movie) {
		return movieService.save(movie);
	}

	@GetMapping("/get/all")
	public List<MovieModel> getAllMovies() {
		return movieService.getAllMovies();
	}

	@DeleteMapping("/delete/name={movieTitle}")
	public ResponseEntity<String> deleteMovie(@PathVariable String movieTitle) {
		movieService.deleteMovie(movieTitle);
		return ResponseEntity.ok("Movie deleted successfully!");
	}

	@PutMapping("/update/id={movieId}")
	public ResponseEntity<Object> updateMovie(@PathVariable String movieId, @RequestBody MovieModel updatedMovie) {
		Object movie = movieService.updateMovie(movieId, updatedMovie);
		return ResponseEntity.ok(movie);
	}

	@GetMapping("/get/id={movieId}")
	public MovieModel getMovieById(@PathVariable String movieId) {
		return movieService.getMovieById(movieId);

	}

	@GetMapping("/get/byFilter")
	public List<MovieModel> getMovies(@RequestParam(required = false) String genre,
			@RequestParam(required = false) String date, @RequestParam(required = false) String movieTitle) {

		if (genre != null) {
			return movieService.getMoviesByGenre(genre);
		} else if (date != null) {
			return movieService.getMoviesByDate(date);
		} else if (movieTitle != null) {
			return movieService.getMoviesByTitle(movieTitle);
		} else {
			return List.of();
		}
	}
}
