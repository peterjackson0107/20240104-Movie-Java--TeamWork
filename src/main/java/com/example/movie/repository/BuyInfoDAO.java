package com.example.movie.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.BuyInfo;
import com.example.movie.entity.MovieInfo;
import com.example.movie.entity.User;

@Repository
public interface BuyInfoDAO extends JpaRepository<BuyInfo, Integer>{
	
	@Transactional
	public int deleteByNumber(int number);
	
	public Optional<BuyInfo> findByNumber(int number);
	
	public List<BuyInfo> findByAccount(String account);
	
	public List<BuyInfo> findAllByMovieIdAndCinemaAndAreaAndOnDateAndOnTime(int movieId,String cinema,String area,LocalDate onDate,String onTime);
	
//	public List<BuyInfo> findBySeatIn(String seat);
	
	@Query("select new BuyInfo(seat) from BuyInfo where movieId = ?1 and cinema = ?2 and area = ?3 and onDate = ?4 and onTime = ?5")
	public List<BuyInfo> findSeatByMovieIdAndCinemaAndAreaAndOnDateAndOntime(int movieId,String cinema,String area,LocalDate onDate,String onTime);
	
//	public boolean existsByMovieAndCinemaAndAreaAndSeatContaining(String movie,String cinema,String area,String seat);
	
//	public List<MovieInfo> findByMovieContainingAndCinemaContainingAndAreaContainingAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
//			String movie, String cinema, String area, LocalDate startDate, LocalDate endDate);
	
	
	@Query(value = "select sum(b.price) as total from buyinfo b where b.movie_id = :movieId and (b.cinema = :cinema or :cinema = '' or :cinema is null)",nativeQuery = true)
	public Integer movieprofit(
			@Param("movieId") int movieId,
			@Param("cinema") String cinema);
	
//	@Transactional
//	@Modifying
//	@Query("update MovieInfo set movie = :movie,cinema = :cinema,area = :area, price = :price,"
//			+ " startDate = :startDate, endDate = :endDate, onDate = :onDate, time = :time "
//			+ " where order = :order")
//	public int updateInfo(
//			@Param("order") int order,
//			@Param("movie") String movie,
//			@Param("cinema") String cinema,
//			@Param("area") String area,
//			@Param("price") int price,
//			@Param("startDate") LocalDate startDate,
//			@Param("endDate") LocalDate endDate,
//			@Param("onDate") LocalDate onDate,
//			@Param("time") String time);
}
