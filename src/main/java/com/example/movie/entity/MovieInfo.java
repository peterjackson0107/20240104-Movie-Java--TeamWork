package com.example.movie.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movieinfo")
public class MovieInfo {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "number")
	private int number;
	
	@Column(name = "movie_id")
	private String movieId;
	
	@Column(name = "movie_name")
	private String movie;
	
	@Column(name = "cinema")
	private String cinema;
	
	@Column(name = "area")
	private String area;
	
	@Column(name = "price")
	private int price;

	@Column(name = "on_date")
	private LocalDate onDate;
	
	@Column(name = "on_time")
	private String onTime;
	
	@Column(name = "on_sell")
	private boolean onSell;

	public MovieInfo() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MovieInfo(int number,String movieId, String movie, String cinema, String area, int price, LocalDate onDate, String onTime,boolean onSell) {
		super();
		this.number = number;
		this.movieId = movieId;
		this.movie = movie;
		this.cinema = cinema;
		this.area = area;
		this.price = price;
		this.onDate = onDate;
		this.onTime = onTime;
		this.onSell = onSell;
	}
	
	public MovieInfo(String movie, String cinema, String area, int price, LocalDate onDate, String onTime,boolean onSell) {
		super();
		this.movie = movie;
		this.cinema = cinema;
		this.area = area;
		this.price = price;
		this.onDate = onDate;
		this.onTime = onTime;
		this.onSell = onSell;
	}
	
	public MovieInfo(String movieId,String movie, String cinema, String area, int price, LocalDate onDate, String onTime,boolean onSell) {
		super();
		this.movieId = movieId;
		this.movie = movie;
		this.cinema = cinema;
		this.area = area;
		this.price = price;
		this.onDate = onDate;
		this.onTime = onTime;
		this.onSell = onSell;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getOnDate() {
		return onDate;
	}

	public void setOnDate(LocalDate onDate) {
		this.onDate = onDate;
	}

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}



	public boolean isOnSell() {
		return onSell;
	}



	public void setOnSell(boolean onSell) {
		this.onSell = onSell;
	}
	
	
	
}
