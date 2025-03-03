package com.movieapp.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.movieapp.booking.model.MovieBooking;

@Repository
public interface BookingRepository extends MongoRepository<MovieBooking, String> {
}

