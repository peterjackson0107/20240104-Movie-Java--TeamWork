package com.example.movie.vo;

public class UserLoginRes {
	
	private int code;
	
	private String rtnCode;

	public UserLoginRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginRes(int code, String rtnCode) {
		super();
		this.code = code;
		this.rtnCode = rtnCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	
	
	
	
	
}
