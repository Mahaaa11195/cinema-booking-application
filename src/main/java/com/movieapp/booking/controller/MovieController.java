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
@RequestMapping
public class MovieController {

    @Autowired
    private MovieService movieService;

    public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}

	// Save a single movie
    
    @PostMapping("/movie")
    public ResponseEntity<?> addMovie(@RequestBody MovieModel movie) {
        return movieService.save(movie);
    }

    // Get all movies
    
    @GetMapping("/movies")
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }
    //delete movie 
    
    @DeleteMapping("/movie/id={movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable String movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok("Movie deleted successfully!");
    }
    
    //update movie 

    @PutMapping("/movie/id={movieId}")
    public ResponseEntity<Object> updateMovie(
            @PathVariable String movieId, 
            @RequestBody MovieModel updatedMovie
    ) {
        Object movie = movieService.updateMovie(movieId, updatedMovie);
        return ResponseEntity.ok(movie);
    }
    
 
    
    @GetMapping("/movie/{movieId}")
    public MovieModel getMovieById (@PathVariable String movieId ) {
    	return movieService.getMovieById(movieId);
    	
    }
    @GetMapping("/movie")
    public List<MovieModel> getMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String movieTitle
//            ,@RequestParam(required = false) String location
            ) {
        
        if (genre != null) {
            return movieService.getMoviesByGenre(genre);
        } else if (date != null) {
            return movieService.getMoviesByDate(date);
        } else if (movieTitle != null) {
            return movieService.getMoviesByTitle(movieTitle);
        } 
//        else if (location != null) {
//            return movieService.getMoviesByLocation(location);
//        } 
        else {
            return List.of();  // Return empty list if no filter is provided
        }
    }

}
