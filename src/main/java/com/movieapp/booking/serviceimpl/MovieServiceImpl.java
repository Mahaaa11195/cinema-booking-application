package com.movieapp.booking.serviceimpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movieapp.booking.exception.ResourceNotFoundException;
import com.movieapp.booking.model.LocationModel;
import com.movieapp.booking.model.MovieModel;
import com.movieapp.booking.model.ShowDateModel;
import com.movieapp.booking.model.ShowModel;
import com.movieapp.booking.repository.MovieRepository;
import com.movieapp.booking.service.MovieService;
import com.movieapp.booking.utils.PropertyConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private PropertyConstants properties;

	@Override
	public ResponseEntity<?> save(MovieModel movie) {

		Optional<MovieModel> existingMovie = repository.findByMovieTitle(movie.getMovieTitle());
		if (existingMovie.isPresent()) {
			return new ResponseEntity<>("Movie with title '" + movie.getMovieTitle() + "' already exists!",
					HttpStatus.CONFLICT);
		}

		LocalDateTime movieAddTime = LocalDateTime.now();
		movie.setDate(movieAddTime);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate releaseLocalDate = LocalDate.parse(movie.getReleaseDate(), formatter);
		LocalDateTime releaseLocalDateTime = releaseLocalDate.atStartOfDay();

		List<String> defaultShowTimes = properties.DEFAULT_SHOW_TIME;

		// Generate list of dates with shows from release date to next 6 days
		List<ShowDateModel> showDates = new ArrayList<>();
		/*
		 * improve logic for: 1. running week booking 2. dont book for previous day
		 * after release
		 */
		for (int i = 0; i <= 6; i++) {
			LocalDateTime showDate = releaseLocalDateTime.plusDays(i);

			// Create shows for each date
			List<ShowModel> shows = new ArrayList<>();
			for (String time : defaultShowTimes) {
				shows.add(new ShowModel(time));
			}
			showDates.add(new ShowDateModel(showDate, shows));
		}
		// Assign the generated dates with shows to each location
		if (movie.getLocations() != null) {
			for (LocationModel location : movie.getLocations()) {
				location.setDates(showDates);
			}
		}
		repository.save(movie);
		log.info("movie added with values: {}", movie);
		return new ResponseEntity<>("Movie saved successfully!", HttpStatus.CREATED);
	}

	@Override
	public List<MovieModel> getAllMovies() {
		return repository.findAll();
	}

	// having issue. change it to delte by moviename
	@Override
	public void deleteMovie(String movieName) {
		// db.getcollection.({movietitle: {$in: [/abcd/, /avenger/}})
		repository.deleteByMovieTitle(movieName);
	}

	@Override
	public Optional<MovieModel> updateMovie(String movieId, MovieModel updatedMovie) {

		return repository.findById(movieId).map(movie -> {
			movie.setMovieTitle(updatedMovie.getMovieTitle());
			movie.setGenre(updatedMovie.getGenre());
			movie.setReleaseDate(updatedMovie.getReleaseDate());
			movie.setLocations(updatedMovie.getLocations());

			LocalDateTime movieAddTime = LocalDateTime.now();
			movie.setDate(movieAddTime);

			return repository.save(movie);
		});
	}

	@Override
	public MovieModel getMovieById(String movieId) {

		return repository.findById(movieId)
				.orElseThrow(() -> new ResourceNotFoundException("MovieModel", "movieId", movieId));
	}

	@Override
	public List<MovieModel> getMoviesByGenre(String genre) {
		return repository.findByGenre(genre);
	}

	@Override
	public List<MovieModel> getMoviesByDate(String date) {
		return repository.findByDate(date);
	}

	@Override
	public List<MovieModel> getMoviesByTitle(String movieTitle) {
		return repository.findByMovieTitleContainingIgnoreCase(movieTitle);
	}
//	@Override
//	    public List<MovieModel> getMoviesByLocation(String location) {
//	        return repository.findByLocationContainingIgnoreCase(location);
//	    }

}