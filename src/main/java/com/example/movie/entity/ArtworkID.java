package com.example.movie.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ArtworkID implements Serializable {
	
	private String movie;
	
	private String account;

	public ArtworkID() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtworkID(String movie, String account) {
		super();
		this.movie = movie;
		this.account = account;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	

}
