package com.example.movie.vo;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyInfoReq {
	
	private String mail;
	
	private int number;
	
	private String account;
	
	private String movie;
	
	private int movieId;

	private String cinema;
	
	private String area;
	
	private int price;
	
	private LocalDate onDate;
	
	private String onTime;
	
	private String seat;
	
	private boolean confirmpay;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isConfirmpay() {
		return confirmpay;
	}

	public void setConfirmpay(boolean confirmpay) {
		this.confirmpay = confirmpay;
	}
	
	
	
}
