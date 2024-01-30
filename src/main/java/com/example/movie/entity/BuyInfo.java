package com.example.movie.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "buyinfo")
public class BuyInfo {
	
	@Id
	@Column(name = "number")
	private int number;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "movie_id")
	private int movieId;
	
	@Column(name = "movie")
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
	
	@Column(name = "seat")
	private String seat;
	
	@Column(name = "confirmpay")
	private boolean confirmpay;

	public BuyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public BuyInfo(String seat) {
		super();
		this.seat = seat;
	}
	
	public BuyInfo(String movie,int movieId, String cinema, String area) {
		super();
		this.movie = movie;
		this.movieId = movieId;
		this.cinema = cinema;
		this.area = area;
	}



	public BuyInfo(int number, String account, String movie,int movieId, String cinema, String area, int price, LocalDate onDate,
			String onTime, String seat,boolean confirmpay) {
		super();
		this.number = number;
		this.account = account;
		this.movie = movie;
		this.movieId = movieId;
		this.cinema = cinema;
		this.area = area;
		this.price = price;
		this.onDate = onDate;
		this.onTime = onTime;
		this.seat = seat;
		this.confirmpay = confirmpay;
	}
	
	public BuyInfo(String account, String movie,int movieId, String cinema, String area, int price, LocalDate onDate,
			String onTime, String seat,boolean confirmpay) {
		super();
		this.account = account;
		this.movie = movie;
		this.movieId = movieId;
		this.cinema = cinema;
		this.area = area;
		this.price = price;
		this.onDate = onDate;
		this.onTime = onTime;
		this.seat = seat;
		this.confirmpay = confirmpay;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
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

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}



	public int getMovieId() {
		return movieId;
	}



	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}



	public boolean isConfirmpay() {
		return confirmpay;
	}



	public void setConfirmpay(boolean confirmpay) {
		this.confirmpay = confirmpay;
	}

	
	
}
