package com.example.movie.vo;

public class ArtworkReq {
	
	private int artorder;
	
	private String movie;
	
	private String movieId;
	
	private String account;
	
	private String artname;
	
	private String artlocation;

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public int getArtorder() {
		return artorder;
	}

	public void setArtorder(int artorder) {
		this.artorder = artorder;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getArtname() {
		return artname;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public void setArtname(String artname) {
		this.artname = artname;
	}

	public String getArtlocation() {
		return artlocation;
	}

	public void setArtlocation(String artlocation) {
		this.artlocation = artlocation;
	}
	
	
	
	
	
}
