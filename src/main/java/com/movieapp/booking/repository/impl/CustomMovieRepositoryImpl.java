//package com.movieapp.booking.repository.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.stereotype.Repository;
//
//import com.movieapp.booking.model.MovieModel;
//import com.movieapp.booking.repository.CustomRepo;
//
//@Repository
//public class CustomMovieRepositoryImpl implements CustomRepo {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public List<MovieModel> findByFilters(String genre, String date, String title, String location) {
//        Query query = new Query();
//
//        if (genre != null && !genre.isEmpty()) {
//            query.addCriteria(Criteria.where("genre").is(genre));
//        }
//        if (date != null && !date.isEmpty()) {
//            query.addCriteria(Criteria.where("date").is(date));
//        }
//        if (title != null && !title.isEmpty()) {
//            query.addCriteria(Criteria.where("title").regex(title, "i")); // Case-insensitive title search
//        }
//        if (location != null && !location.isEmpty()) {
//            query.addCriteria(Criteria.where("location").is(location));
//        }
//
//        return mongoTemplate.find(query, MovieModel.class);
//    }
//}
