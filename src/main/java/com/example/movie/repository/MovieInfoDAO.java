package com.example.movie.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.MovieInfo;

@Repository
public interface MovieInfoDAO extends JpaRepository<MovieInfo, Integer>{
	
	@Transactional
	public int deleteByNumber(int number);
	
	public Optional<MovieInfo> findByNumber(int number);
	
	public Optional<MovieInfo> findAllByMovieId(int movieId);
	
	public List<MovieInfo> findByMovieIdContainingAndCinemaContainingAndOnDateBetween(
			String movieId, String cinema, LocalDate startDate, LocalDate endDate);
	
	public List<MovieInfo> findByCinemaAndAreaAndOnDate(
			String cinema, String area, LocalDate onDate);
	
	@Transactional
	@Modifying
	@Query("update MovieInfo set movie = :movie,cinema = :cinema,area = :area, price = :price,"
			+ " startDate = :startDate, endDate = :endDate, onDate = :onDate, time = :time "
			+ " where order = :order")
	public int updateInfo(
			@Param("order") int order,
			@Param("movie") String movie,
			@Param("cinema") String cinema,
			@Param("area") String area,
			@Param("price") int price,
			@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate,
			@Param("onDate") LocalDate onDate,
			@Param("time") String time);
}
