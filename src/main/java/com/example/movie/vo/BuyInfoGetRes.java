package com.example.movie.vo;

import java.util.List;

import com.example.movie.entity.BuyInfo;

public class BuyInfoGetRes extends UserLoginRes {

    private List<BuyInfo> Buyinfo;

    
    public BuyInfoGetRes() {
    }

    public BuyInfoGetRes(int code, String message, List<BuyInfo> Buyinfo) {
        super(code, message);
        this.Buyinfo = Buyinfo;
    }

    public List<BuyInfo> getBuyInfoList() {
        return Buyinfo;
    }

    public void setBuyInfoList(List<BuyInfo> Buyinfo) {
        this.Buyinfo = Buyinfo;
    }
}
