package com.movieapp.booking.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movieapp.booking.exception.ResourceNotFoundException;
import com.movieapp.booking.model.MovieModel;
import com.movieapp.booking.repository.MovieRepository;
import com.movieapp.booking.service.MovieService;

@Service
public class ServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repository;

	@Override
	public ResponseEntity<?> save(MovieModel movie) {
		Optional<MovieModel> existingMovie = repository.findByMovieTitle(movie.getMovieTitle());

		if (existingMovie.isPresent()) {
			return new ResponseEntity<>("Movie with title '" + movie.getMovieTitle() + "' already exists!",
					HttpStatus.CONFLICT);
		}

		repository.save(movie);
		return new ResponseEntity<>("Movie saved successfully!", HttpStatus.CREATED);
	}

	@Override
	public List<MovieModel> getAllMovies() {
		return repository.findAll();
	}

	@Override
	public void deleteMovie(String movieId) {
		repository.deleteById(movieId);
	}

	@Override
	public Optional<MovieModel> updateMovie(String movieId, MovieModel updatedMovie) {
		return repository.findById(movieId).map(movie -> {
			movie.setMovieTitle(updatedMovie.getMovieTitle());
			movie.setGenre(updatedMovie.getGenre());
			movie.setDate(updatedMovie.getDate());
			movie.setLocation(updatedMovie.getLocation());
			return repository.save(movie); // ✅ Ensure you return the saved movie
		});
	}

  

	@Override
	public MovieModel getMovieById(String movieId) {

		return repository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("MovieModel", "movieId", movieId));
	}
	
	@Override
	public List<MovieModel> getMovieByParams(String movieTitle, String genre, String date, String location) {
		
		List<MovieModel> allMovies = repository.findAll(); // Fetch all movies from the data source

	    // Filter based on the parameters provided
	    return allMovies.stream()
	            .filter(movie -> (movieTitle == null || movie.getMovieTitle().equalsIgnoreCase(movieTitle)) && 
	                             (genre == null || movie.getGenre().equalsIgnoreCase(genre)) &&
	                             (date == null || movie.getDate().equals(date)) &&
	                             (location == null || movie.getLocation().equalsIgnoreCase(location)))
	            .collect(Collectors.toList());
	}

}
