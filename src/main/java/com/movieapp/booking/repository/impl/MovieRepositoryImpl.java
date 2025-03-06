//package com.movieapp.booking.repository.impl;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
//import org.springframework.stereotype.Repository;
//
//import com.movieapp.booking.model.MovieModel;
//import com.movieapp.booking.repository.MovieRepository;
//
//@Repository
//public class MovieRepositoryImpl implements MovieRepository {
//
//	@Override
//	public <S extends MovieModel> S insert(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> List<S> insert(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> List<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MovieModel> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MovieModel> findAllById(Iterable<String> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsById(String id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(String id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void delete(MovieModel entity) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAllById(Iterable<? extends String> ids) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends MovieModel> entities) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public List<MovieModel> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<MovieModel> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public <S extends MovieModel> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends MovieModel> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public <S extends MovieModel> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public <S extends MovieModel, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<MovieModel> findById(String id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<MovieModel> findByMovieTitle(String movieTitle) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public List<MovieModel> findByGenre(String genre) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MovieModel> findByDate(String date) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<MovieModel> findByMovieTitleContainingIgnoreCase(String movieTitle) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
