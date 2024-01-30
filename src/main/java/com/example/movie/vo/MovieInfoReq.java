package com.example.movie.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieInfoReq {
	
	private int number;
	
	@JsonProperty("movie")
	private String movie;
	
	private String movieId;
	
	private String cinema;
	
	private String area;
	
	private int price;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private LocalDate onDate;
	
	private String onTime;
	
	private boolean onSell;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
