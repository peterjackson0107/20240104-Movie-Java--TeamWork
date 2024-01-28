package com.example.movie.service.ifs;

import java.time.LocalDate;
import java.util.Collection;

import com.example.movie.vo.UserLoginRes;

public interface BuyInfoService {
	
	public UserLoginRes create(String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat);

	public UserLoginRes update(int number, String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat);
	
	public UserLoginRes delete(int number);
	
	public UserLoginRes search(String account);
	
	public UserLoginRes searchseat(int movieId,String cinema,String area,LocalDate onDate,String time);
	
//	public UserLoginRes search(String movie, String cinema, String area, 
//			LocalDate startDate, LocalDate endDate);

}
