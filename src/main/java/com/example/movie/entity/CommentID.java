package com.example.movie.entity;


class CommentId {

    private String movie;

    private int commentIndex;

	public CommentId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentId(String movie, int commentIndex) {
		super();
		this.movie = movie;
		this.commentIndex = commentIndex;
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
    
    
}
