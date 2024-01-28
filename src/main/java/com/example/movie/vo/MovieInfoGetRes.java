package com.example.movie.vo;

import java.util.List;

import com.example.movie.entity.MovieInfo;

public class MovieInfoGetRes extends UserLoginRes {

    private List<MovieInfo> movieinfo;

    
    public MovieInfoGetRes() {
    }

    public MovieInfoGetRes(int code, String message, List<MovieInfo> movieinfo) {
        super(code, message);
        this.movieinfo = movieinfo;
    }

    public List<MovieInfo> getMovieInfoList() {
        return movieinfo;
    }

    public void setMovieInfoList(List<MovieInfo> movieinfo) {
        this.movieinfo = movieinfo;
    }
}
