package com.example.movie.vo;

import java.util.List;
import java.util.Optional;

import com.example.movie.entity.Mypage;

public class MypageGetRes extends UserLoginRes {
	
	private Optional<Mypage> mypageList;
	
	private List<Mypage> pageList;
	
    public MypageGetRes() {
    }

    public MypageGetRes(int code, String message, Optional<Mypage> mypageList) {
        super(code, message);
        this.mypageList = mypageList;
    }
    
    public MypageGetRes(int code, String message, List<Mypage> pageList) {
        super(code, message);
        this.pageList = pageList;
    }

	public Optional<Mypage> getMypageList() {
		return mypageList;
	}

	public void setMypageList(Optional<Mypage> mypageList) {
		this.mypageList = mypageList;
	}

	public List<Mypage> getPageList() {
		return pageList;
	}

	public void setPageList(List<Mypage> pageList) {
		this.pageList = pageList;
	}
    
	
    

}
