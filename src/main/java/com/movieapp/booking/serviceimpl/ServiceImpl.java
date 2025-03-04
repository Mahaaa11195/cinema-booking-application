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

@Service
public class ServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repository;

	@Override
//	public ResponseEntity<?> save(MovieModel movie) {
//		Optional<MovieModel> existingMovie = repository.findByMovieTitle(movie.getMovieTitle());
//		LocalDateTime currentTime = LocalDateTime.now();
//		movie.setDate(currentTime);
//		if (existingMovie.isPresent()) {
//			return new ResponseEntity<>("Movie with title '" + movie.getMovieTitle() + "' already exists!",
//					HttpStatus.CONFLICT);
//		}
//		// Convert releaseDate (String) to LocalDate
//	    LocalDate releaseLocalDate = LocalDate.parse(movie.getReleaseDate());
//
//	    // Generate list of dates from release date to the next 2 days
//	    List<ShowDateModel> showDates = new ArrayList<>();
//	    for (int i = 0; i <= 2; i++) {
//	        showDates.add(new ShowDateModel(releaseLocalDate.plusDays(i), new ArrayList<>()));
//	    }
//
//	    // Update each location with the generated dates
//	    if (movie.getLocations() != null) {
//	        for (LocationModel location : movie.getLocations()) {
//	            location.setDates(showDates);
//	        }
//	    }
//		repository.save(movie);
//		return new ResponseEntity<>("Movie saved successfully!", HttpStatus.CREATED);
//	}
	public ResponseEntity<?> save(MovieModel movie) {
	    Optional<MovieModel> existingMovie = repository.findByMovieTitle(movie.getMovieTitle());
	    LocalDateTime currentTime = LocalDateTime.now();
	    movie.setDate(currentTime);
	    
	    if (existingMovie.isPresent()) {
	        return new ResponseEntity<>("Movie with title '" + movie.getMovieTitle() + "' already exists!", HttpStatus.CONFLICT);
	    }

	    // Convert releaseDate (String) to LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format if needed
	    LocalDate releaseLocalDate = LocalDate.parse(movie.getReleaseDate(), formatter);
	    LocalDateTime releaseLocalDateTime = releaseLocalDate.atStartOfDay();
//	    movie.setReleaseDate(releaseLocalDateTime); 
	    // Default showtimes
	    List<String> defaultShowTimes = List.of("10 AM", "2 PM", "6 PM", "9 PM");

	    // Generate list of dates with shows from release date to next 2 days
	    List<ShowDateModel> showDates = new ArrayList<>();
	    for (int i = 0; i <= 6; i++) {
	        LocalDateTime showDate = releaseLocalDateTime.plusDays(i);

	        // Create shows for each date
	        List<ShowModel> shows = new ArrayList<>();
	        for (String time : defaultShowTimes) {
	            shows.add(new ShowModel(time));
	        }
	        System.out.println("showDate: " + showDate);
	        showDates.add(new ShowDateModel(showDate, shows));
	    }
	    System.out.println("Raw release date: " + movie.getReleaseDate());
	    System.out.println("Parsed release date: " + releaseLocalDateTime);
	    // Assign the generated dates with shows to each location
	    if (movie.getLocations() != null) {
	        for (LocationModel location : movie.getLocations()) {
	            location.setDates(showDates);
	        }
	    }
	    System.out.println("showDates: " + showDates);
	    System.out.println("movie: " + movie);
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
//			movie.setLocation(updatedMovie.getLocation());
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