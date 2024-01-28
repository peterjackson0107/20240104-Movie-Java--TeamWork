package com.example.movie.vo;

import java.util.List;

import com.example.movie.entity.BuyInfo;
import com.example.movie.entity.Comment;

public class CommentGetRes extends UserLoginRes {

    private List<Comment> Comment;

    
    public CommentGetRes() {
    }

    public CommentGetRes(int code, String message, List<Comment> Comment) {
        super(code, message);
        this.Comment = Comment;
    }

    public List<Comment> getCommentList() {
        return Comment;
    }

    public void setCommentList(List<Comment> Comment) {
        this.Comment = Comment;
    }
}
