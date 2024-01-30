package com.example.movie.vo;

import java.util.List;

import com.example.movie.entity.BuyInfo;

public class BuyInfoGetRes extends UserLoginRes {

    private List<BuyInfo> Buyinfo;
    
    private Integer Buyprofit;

    
    public BuyInfoGetRes() {
    }

    public BuyInfoGetRes(int code, String message, List<BuyInfo> Buyinfo) {
        super(code, message);
        this.Buyinfo = Buyinfo;
    }
    
    public BuyInfoGetRes(int code, String message, Integer Buyprofit) {
        super(code, message);
        this.Buyprofit = Buyprofit;
    }

    public List<BuyInfo> getBuyInfoList() {
        return Buyinfo;
    }

    public void setBuyInfoList(List<BuyInfo> Buyinfo) {
        this.Buyinfo = Buyinfo;
    }

	public Integer getBuyprofit() {
		return Buyprofit;
	}

	public void setBuyprofit(Integer buyprofit) {
		Buyprofit = buyprofit;
	}
    
    
    
}
