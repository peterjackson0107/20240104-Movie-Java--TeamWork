package com.example.movie.service.ifs;

import java.time.LocalDate;
import java.util.Collection;

import com.example.movie.vo.UserLoginRes;

public interface BuyInfoService {
	
	public UserLoginRes create(String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat,boolean confirmpay);

	public UserLoginRes update(int number, String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat,boolean confirmpay);
	
	public UserLoginRes paycheck(int number);
	
	public UserLoginRes delete(int number);
	
	public UserLoginRes search(String account);
	
	public UserLoginRes searchP(int movieId,String cinema);
	
	public UserLoginRes searchseat(int movieId,String cinema,String area,LocalDate onDate,String time);
	
	void sendBuyEmail(String userEmail, String account,String movie, String cinema, String area, int price,
			LocalDate onDate, String time, String seat,String buycode);
	
	void sendEmail(String userEmail, String account,String movie, String cinema, String area, int price,
			LocalDate onDate, String time, String seat);
	
//	public UserLoginRes search(String movie, String cinema, String area, 
//			LocalDate startDate, LocalDate endDate);

}
