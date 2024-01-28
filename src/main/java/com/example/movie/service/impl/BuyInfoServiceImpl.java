package com.example.movie.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.BuyInfo;
import com.example.movie.repository.BuyInfoDAO;
import com.example.movie.service.ifs.BuyInfoService;
import com.example.movie.vo.BuyInfoGetRes;
import com.example.movie.vo.UserLoginRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BuyInfoServiceImpl implements BuyInfoService {
	
    @Autowired
    private BuyInfoDAO buyInfoDao;

    @Override
    public UserLoginRes create(String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat) {
        if (!StringUtils.hasText(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        if (!StringUtils.hasText(movie)) {
            return new UserLoginRes(RtnCode.CHECK_MOVIE_INPUT.getCode(),RtnCode.CHECK_MOVIE_INPUT.getMessage());
        }
        if (!StringUtils.hasText(cinema)) {
            return new UserLoginRes(RtnCode.CHECK_CINEMA_INPUT.getCode(),RtnCode.CHECK_CINEMA_INPUT.getMessage());
        }
        if (!StringUtils.hasText(area)) {
            return new UserLoginRes(RtnCode.CHECK_AREA_INPUT.getCode(),RtnCode.CHECK_AREA_INPUT.getMessage());
        }
        if (price==0) {
            return new UserLoginRes(RtnCode.CHECK_PRICE_INPUT.getCode(),RtnCode.CHECK_PRICE_INPUT.getMessage());
        }
        if (onDate==null) {
            return new UserLoginRes(RtnCode.CHECK_ONDATE_INPUT.getCode(),RtnCode.CHECK_ONDATE_INPUT.getMessage());
        }
        if (!StringUtils.hasText(time)) {
            return new UserLoginRes(RtnCode.CHECK_ONTIME_INPUT.getCode(),RtnCode.CHECK_ONTIME_INPUT.getMessage());
        }
        if (!StringUtils.hasText(seat)) {
            return new UserLoginRes(RtnCode.CHECK_SEAT_INPUT.getCode(),RtnCode.CHECK_SEAT_INPUT.getMessage());
        }
        seat = seat.replace(" ", "").replace("[", "").replace("]", "");
        List<String> seatOrderList = new ArrayList<>(Arrays.asList(seat.split(",")));
        List<BuyInfo> buyInfoList = buyInfoDao.findSeatByMovieIdAndCinemaAndAreaAndOnDateAndOntime(movieId, cinema, area,onDate,time);
        for(String item : seatOrderList) {
            for(BuyInfo seatItem : buyInfoList) {
    			if (seatItem.getSeat().contains(item)) {
    				return new UserLoginRes(RtnCode.DUPLICATE_SEAT.getCode(), RtnCode.DUPLICATE_SEAT.getMessage());
    			}
        		
            }
        }

        buyInfoDao.save(new BuyInfo(account,movie,movieId,cinema,area,price,onDate,time,seat));
        
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
	@Override
	public UserLoginRes update(int number, String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat) {
        if (!StringUtils.hasText(seat)) {
            return new UserLoginRes(RtnCode.CHECK_SEAT_INPUT.getCode(),RtnCode.CHECK_SEAT_INPUT.getMessage());
        }
        seat = seat.replace(" ", "").replace("[", "").replace("]", "");
        List<String> seatOrderList = new ArrayList<>(Arrays.asList(seat.split(",")));
        List<BuyInfo> buyInfoList = buyInfoDao.findSeatByMovieIdAndCinemaAndAreaAndOnDateAndOntime(movieId, cinema, area,onDate,time);
        for(String item : seatOrderList) {
            for(BuyInfo seatItem : buyInfoList) {
    			if (seatItem.getSeat().contains(item)) {
    				return new UserLoginRes(RtnCode.DUPLICATE_SEAT.getCode(), RtnCode.DUPLICATE_SEAT.getMessage());
    			}
        		
            }
        }
        Optional<BuyInfo> op = buyInfoDao.findByNumber(number);
        BuyInfo buyinfo = op.get();
        if (!StringUtils.hasText(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        } else {
        	buyinfo.setAccount(account);
        }
        if (!StringUtils.hasText(movie)) {
            return new UserLoginRes(RtnCode.CHECK_MOVIE_INPUT.getCode(),RtnCode.CHECK_MOVIE_INPUT.getMessage());
        } else {
        	buyinfo.setMovie(movie);
        }
        if (!StringUtils.hasText(cinema)) {
            return new UserLoginRes(RtnCode.CHECK_CINEMA_INPUT.getCode(),RtnCode.CHECK_CINEMA_INPUT.getMessage());
        } else {
        	buyinfo.setCinema(cinema);
        }
        if (!StringUtils.hasText(area)) {
            return new UserLoginRes(RtnCode.CHECK_AREA_INPUT.getCode(),RtnCode.CHECK_AREA_INPUT.getMessage());
        } else {
        	buyinfo.setArea(area);
        }
        if (price==0) {
            return new UserLoginRes(RtnCode.CHECK_PRICE_INPUT.getCode(),RtnCode.CHECK_PRICE_INPUT.getMessage());
        } else {
        	buyinfo.setPrice(price);
        }
        if (onDate==null) {
            return new UserLoginRes(RtnCode.CHECK_ONDATE_INPUT.getCode(),RtnCode.CHECK_ONDATE_INPUT.getMessage());
        } else {
        	buyinfo.setOnDate(onDate);
        }
        if (!StringUtils.hasText(time)) {
            return new UserLoginRes(RtnCode.CHECK_ONTIME_INPUT.getCode(),RtnCode.CHECK_ONTIME_INPUT.getMessage());
        } else {
			buyinfo.setOnTime(time);
        }
		try {
			buyinfo.setSeat(seat);
			buyInfoDao.save(buyinfo);
		} catch (Exception e) {
			return new UserLoginRes(RtnCode.MOVIE_INFO_SAVE_ERROR.getCode(), RtnCode.MOVIE_INFO_SAVE_ERROR.getMessage());
		}
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}


	@Override
	public UserLoginRes delete(int number) {
		int res = buyInfoDao.deleteByNumber(number);
		if(res == 0) {
			return new UserLoginRes(RtnCode.DELETED_BUY_INFO_NOT_EXSISTED.getCode(), RtnCode.DELETED_BUY_INFO_NOT_EXSISTED.getMessage());
		}else {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		}
	}

	@Override
	public UserLoginRes search(String account) {
        if (!StringUtils.hasText(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
		List<BuyInfo> res = new ArrayList<>();
		res = buyInfoDao.findByAccount(account);
		return new BuyInfoGetRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage(),res);
	}
	
	@Override
	public UserLoginRes searchseat(int movieId,String cinema,String area,LocalDate onDate,String time) {
        if (movieId ==0) {
            return new UserLoginRes(RtnCode.CHECK_MOVIE_INPUT.getCode(),RtnCode.CHECK_MOVIE_INPUT.getMessage());
        }
        if (!StringUtils.hasText(cinema)) {
            return new UserLoginRes(RtnCode.CHECK_CINEMA_INPUT.getCode(),RtnCode.CHECK_CINEMA_INPUT.getMessage());
        }
        if (!StringUtils.hasText(area)) {
            return new UserLoginRes(RtnCode.CHECK_AREA_INPUT.getCode(),RtnCode.CHECK_AREA_INPUT.getMessage());
        }
		List<BuyInfo> res = new ArrayList<>();
		res = buyInfoDao.findAllByMovieIdAndCinemaAndAreaAndOnDateAndOnTime(movieId,cinema,area,onDate,time);
		return new BuyInfoGetRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage(),res);
	}

}
