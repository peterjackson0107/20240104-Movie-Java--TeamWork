package com.example.movie.vo;

import java.util.List;
import java.util.Optional;

import com.example.movie.entity.Mypage;
import com.example.movie.entity.User;

public class UserLoginGetRes extends UserLoginRes {
	
	private Optional<User> userList;
	
    public UserLoginGetRes() {
    }

    public UserLoginGetRes(int code, String message, Optional<User> userList) {
        super(code, message);
        this.userList = userList;
    }

	public Optional<User> getMypageList() {
		return userList;
	}

	public void setMypageList(Optional<User> userList) {
		this.userList = userList;
	}

}
