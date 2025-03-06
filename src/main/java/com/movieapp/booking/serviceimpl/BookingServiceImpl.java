//package com.movieapp.booking.serviceimpl;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import com.movieapp.booking.model.MovieBooking;
//import com.movieapp.booking.model.MovieModel;
//import com.movieapp.booking.model.SeatModel;
//import com.movieapp.booking.model.ShowModel;
//import com.movieapp.booking.repository.BookingRepository;
//import com.movieapp.booking.repository.MovieRepository;
//import com.movieapp.booking.service.BookingService;
//
//@Service
//public class BookingServiceImpl implements BookingService{
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    @Autowired
//    private MovieRepository movieRepository;
//
//
//    public ResponseEntity<Map<String, Object>> bookMovie(MovieBooking booking) {
//        Map<String, Object> response = new HashMap<>();
//        
//        Optional<MovieModel> movieOpt = movieRepository.findById(booking.getMovieId());
//        if (booking.getBookedSeats() == null || booking.getBookedSeats().isEmpty()) {
//            response.put("error", "No seats selected for booking!");
//            return ResponseEntity.badRequest().body(response);
//        }
//        if (movieOpt.isEmpty()) {
//            response.put("error", "Movie not found!");
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        MovieModel movie = movieOpt.get();
//        ShowModel selectedShow = movie.getShows().stream()
//            .filter(show -> show.getTime().equals(booking.getTime()))
//            .findFirst()
//            .orElse(null);
//        System.out.println("Selected Show: " + selectedShow);
//        if (selectedShow == null) {
//            response.put("error", "Show not found!");
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        // Validate seat availability
//        for (String seatNumber : booking.getBookedSeats()) {
//            SeatModel seat = selectedShow.getAvailableSeats().stream()
//                .filter(s -> s.getSeatNumber().equals(seatNumber))
//                .findFirst()
//                .orElse(null);
//            System.out.println("seat: " + seat);
//            if (seat == null || seat.isBooked()) {
//                response.put("error", "Seat " + seatNumber + " is already booked!");
//                return ResponseEntity.badRequest().body(response);
//            }
//        }
//
//        // Book seats and calculate total price
//        double totalPrice = 0;
//        for (String seatNumber : booking.getBookedSeats()) {
//            for (SeatModel seat : selectedShow.getAvailableSeats()) {
//                if (seat.getSeatNumber().equals(seatNumber)) {
//                    seat.setBooked(true);
//                    totalPrice += seat.getPrice(); 
//                }
//            }
//        }
//
//        // Save updated movie details
//        movieRepository.save(movie);
//        
//        // Save the booking
//        bookingRepository.save(booking);
//
//        // Prepare response
//        response.put("message", "Booking successful!");
//        response.put("movie", movie.getMovieTitle());
//        response.put("showTime", selectedShow.getTime());
//        response.put("seats", booking.getBookedSeats());
//        response.put("totalPrice", totalPrice);
//
//        return ResponseEntity.ok(response);
//    }
//
//    public List<MovieBooking> getBookingHistory(){
//    	return bookingRepository.findAll();
//    }
//    
//}

package com.movieapp.booking.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.movieapp.booking.model.LocationModel;
import com.movieapp.booking.model.MovieBooking;
import com.movieapp.booking.model.MovieModel;
import com.movieapp.booking.model.SeatModel;
import com.movieapp.booking.model.ShowDateModel;
import com.movieapp.booking.model.ShowModel;
import com.movieapp.booking.repository.BookingRepository;
import com.movieapp.booking.repository.MovieRepository;
import com.movieapp.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private MovieRepository movieRepository;

	public ResponseEntity<Map<String, Object>> bookMovie(MovieBooking booking) {
		
		Map<String, Object> response = new HashMap<>();
		Optional<MovieModel> movieOpt = movieRepository.findById(booking.getMovieId());

		if (booking.getBookedSeats() == null || booking.getBookedSeats().isEmpty()) {
			response.put("error", "No seats selected for booking!");
			return ResponseEntity.badRequest().body(response);
		}
		if (movieOpt.isEmpty()) {
			response.put("error", "Movie not found!");
			return ResponseEntity.badRequest().body(response);
		}

		MovieModel movie = movieOpt.get();

		// Find the correct location
		LocationModel selectedLocation = movie.getLocations().stream()
				.filter(loc -> loc.getLocationName().equals(booking.getLocation())).findFirst().orElse(null);

		if (selectedLocation == null) {
			response.put("error", "Location not found!");
			return ResponseEntity.badRequest().body(response);
		}

		// Find the correct show date
		ShowDateModel selectedDate = selectedLocation.getDates().stream()
				.filter(date -> date.getDate().toString().equals(booking.getDate())).findFirst().orElse(null);

		if (selectedDate == null) {
			response.put("error", "Show date not found!");
			return ResponseEntity.badRequest().body(response);
		}

		// Find the correct show
		ShowModel selectedShow = selectedDate.getShows().stream()
				.filter(show -> show.getTime().equals(booking.getTime())).findFirst().orElse(null);

		if (selectedShow == null) {
			response.put("error", "Show not found!");
			return ResponseEntity.badRequest().body(response);
		}

		// Validate seat availability
		for (String seatNumber : booking.getBookedSeats()) {
			SeatModel seat = selectedShow.getAvailableSeats().stream().filter(s -> s.getSeatNumber().equals(seatNumber))
					.findFirst().orElse(null);

			if (seat == null || seat.isBooked()) {
				response.put("error", "Seat " + seatNumber + " is already booked!");
				return ResponseEntity.badRequest().body(response);
			}
		}

		// Book seats and calculate total price
		double totalPrice = 0;
		for (String seatNumber : booking.getBookedSeats()) {
			for (SeatModel seat : selectedShow.getAvailableSeats()) {
				if (seat.getSeatNumber().equals(seatNumber)) {
					seat.setBooked(true);
					totalPrice += seat.getPrice();
				}
			}
		}

		// Save updated movie details
		movieRepository.save(movie);

		// Save the booking
		booking.setTotalPrice((int) totalPrice);
		bookingRepository.save(booking);

		// Prepare response
		response.put("message", "Booking successful!");
		response.put("movie", movie.getMovieTitle());
		response.put("location", selectedLocation.getLocationName());
		response.put("date", booking.getDate());
		response.put("showTime", selectedShow.getTime());
		response.put("seats", booking.getBookedSeats());
		response.put("totalPrice", totalPrice);

		return ResponseEntity.ok(response);
	}

	public List<MovieBooking> getBookingHistory() {
		return bookingRepository.findAll();
	}
}
