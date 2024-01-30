package com.example.movie.service.ifs;

import java.time.LocalDate;

import com.example.movie.vo.UserLoginRes;

public interface MovieInfoService {
	
	public UserLoginRes create(String movieId, String movie, String cinema, String area, int price,
			LocalDate onDate, String time,boolean onSell);

	public UserLoginRes update(int number,String movieId, String movie, String cinema, String area, int price,
			LocalDate onDate, String time,boolean onSell);
	
	public UserLoginRes delete(int number);
	
	public UserLoginRes search(String movieId,String movie, String cinema, String area, 
			LocalDate startDate, LocalDate endDate);

}
