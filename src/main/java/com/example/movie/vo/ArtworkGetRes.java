package com.example.movie.vo;

import java.util.List;

import com.example.movie.entity.Artwork;

public class ArtworkGetRes extends UserLoginRes {

    private List<Artwork> artList;

    
    public ArtworkGetRes() {
    }

    public ArtworkGetRes(int code, String message, List<Artwork> artList) {
        super(code, message);
        this.artList = artList;
    }

    public List<Artwork> getArtList() {
        return artList;
    }

    public void setArtList(List<Artwork> artList) {
        this.artList = artList;
    }
}
