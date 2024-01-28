package com.example.movie.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "number")
	private String number;	
	
	@Column(name = "movie_id")
	private int movieID;

	@Column(name = "movie")
	private String movie;

	@Column(name = "comment_index")
	private int commentIndex;

	@Column(name = "comment_index_order")
	private int commentIndexIndex;

	@Column(name = "comment_text")
	private String commentText;

	@Column(name = "comment_time")
	private LocalDateTime commentTime;

	@Column(name = "favorite")
	private int favorite;

	@Column(name = "dislike")
	private int dislike;
	
	@Column(name = "account")
	private String account;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int number,int movieID,String movie, int commentIndex, int commentIndexIndex, String commentText, LocalDateTime commentTime,
			int like, int dislike,String account) {
		super();
		this.movie = movie;
		this.movieID = movieID;
		this.account = account;
		this.commentIndex = commentIndex;
		this.commentIndexIndex = commentIndexIndex;
		this.commentText = commentText;
		this.commentTime = commentTime;
		this.favorite = like;
		this.dislike = dislike;
		this.account = account;
	}

	// Creatㄏノ把计
	public Comment(String movie,int movieID, int commentIndex, String commentText,String account) {
		super();
		this.movie = movie;
		this.movieID = movieID;
		this.account = account;
		this.commentIndex = commentIndex;
		this.commentIndexIndex = 0;
		this.commentText = commentText;
		this.commentTime = LocalDateTime.now();
		this.favorite = 0;
		this.dislike = 0;
	}
	
	// Creatchildㄏノ把计
	public Comment(String movie,int movieID, int commentIndex,int commentIndexIndex, String commentText,String account) {
		super();
		this.movie = movie;
		this.movieID = movieID;
		this.account = account;
		this.commentIndex = commentIndex;
		this.commentIndexIndex = commentIndexIndex;
		this.commentText = commentText;
		this.commentTime = LocalDateTime.now();
		this.favorite = 0;
		this.dislike = 0;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public int getCommentIndex() {
		return commentIndex;
	}

	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}

	public int getCommentIndexIndex() {
		return commentIndexIndex;
	}

	public void setCommentIndexIndex(int commentIndexIndex) {
		this.commentIndexIndex = commentIndexIndex;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	

}
