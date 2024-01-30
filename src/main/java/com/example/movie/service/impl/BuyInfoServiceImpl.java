package com.example.movie.service.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.BuyInfo;
import com.example.movie.repository.BuyInfoDAO;
import com.example.movie.repository.UserDAO;
import com.example.movie.service.ifs.BuyInfoService;
import com.example.movie.vo.BuyInfoGetRes;
import com.example.movie.vo.UserLoginRes;

@Service
public class BuyInfoServiceImpl implements BuyInfoService {
	
	private final JavaMailSender javaMailSender;
	
    @Autowired
    private BuyInfoDAO buyInfoDao;
    
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    public BuyInfoServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public UserLoginRes create(String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat,boolean confirmpay) {
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
        String buyCode = generateVerificationCode();
        
        String email = userDao.findUserEmailByAccount(account);
        
        sendBuyEmail(email,account,movie,cinema,area,price,onDate,time,seat,buyCode);

        buyInfoDao.save(new BuyInfo(account,movie,movieId,cinema,area,price,onDate,time,seat,false));
        
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
	@Override
	public UserLoginRes update(int number, String account, String movie,int movieId, String cinema, String area, int price,
			LocalDate onDate, String time, String seat,boolean confirmpay) {
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
        if (buyinfo.isConfirmpay() == false) {
        	buyinfo.setConfirmpay(confirmpay);
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
		Optional<BuyInfo> op = buyInfoDao.findByNumber(number);
		BuyInfo buyinfo = op.get();
        if (buyinfo.isConfirmpay() == true) {
            return new UserLoginRes(RtnCode.TICKET_IS_PAID.getCode(),RtnCode.TICKET_IS_PAID.getMessage());
        } 
		int res = buyInfoDao.deleteByNumber(number);
		if(res == 0) {
			return new UserLoginRes(RtnCode.DELETED_BUY_INFO_NOT_EXSISTED.getCode(), RtnCode.DELETED_BUY_INFO_NOT_EXSISTED.getMessage());
		}else {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		}
	}

	@Override
	public UserLoginRes paycheck(int number) {
        if (number == 0 ) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
		Optional<BuyInfo> op = buyInfoDao.findById(number);
		BuyInfo buyinfo = op.get();
        if (buyinfo.isConfirmpay() == true ) {
            return new UserLoginRes(RtnCode.TICKET_IS_PAID.getCode(),RtnCode.TICKET_IS_PAID.getMessage());
        }
        
        String email = userDao.findUserEmailByAccount(buyinfo.getAccount());
        
        sendEmail(email,buyinfo.getAccount(),buyinfo.getMovie(),buyinfo.getCinema(),buyinfo.getArea(),buyinfo.getPrice(),buyinfo.getOnDate(),buyinfo.getOnTime(),buyinfo.getSeat());
        
        buyinfo.setConfirmpay(true);
        buyInfoDao.save(buyinfo);
		return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}
	
	
    @Override
    public void sendEmail(String userEmail,String account, String movie, String cinema, String area, int price,
			LocalDate onDate, String time, String seat) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("starlightmoviecinema@gmail.com");
            message.setTo(userEmail);
            message.setSubject("付費成功通知");
            
            // 構建付款頁面URL，將所有訂票相關資訊作為參數添加到URL中
            String paymentPageUrl = "http://localhost:5173/paypage?" +
            						"account=" + URLEncoder.encode(account, StandardCharsets.UTF_8) +
                                    "&movie=" + URLEncoder.encode(movie, StandardCharsets.UTF_8) +
                                    "&cinema=" + URLEncoder.encode(cinema, StandardCharsets.UTF_8) +
                                    "&area=" + URLEncoder.encode(area, StandardCharsets.UTF_8) +
                                    "&price=" + price +
                                    "&onDate=" + onDate.toString() +
                                    "&time=" + URLEncoder.encode(time, StandardCharsets.UTF_8) +
                                    "&seat=" + URLEncoder.encode(seat, StandardCharsets.UTF_8) ;

            // 邮件内容
            String emailContent = "您的訂票詳情如下：\n" +
            		"訂購帳號：" + account + "\n" +
                    "時間：" + onDate.toString() + "，" + time + "\n" +
                    "電影：" + movie + "\n" +
                    "影院：" + cinema + "\n" +
                    "影廳：" + area + "\n" +
                    "價格：" + price + "元\n" +
                    "座位：" + seat + "\n\n" +
                    "付款完畢，祝您觀影愉快！";

            message.setText(emailContent);

            javaMailSender.send(message);

            System.out.println("訂票成功通知郵件已成功發送。");
        } catch (Exception e) {
            e.printStackTrace();
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
	
	@Override
	public UserLoginRes searchP(int movieId,String cinema) {
        if (movieId ==0) {
            return new UserLoginRes(RtnCode.CHECK_MOVIE_INPUT.getCode(),RtnCode.CHECK_MOVIE_INPUT.getMessage());
        }
        Integer res = buyInfoDao.movieprofit(movieId,cinema);
		return new BuyInfoGetRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage(),res);
	}
	
    @Override
    public void sendBuyEmail(String userEmail,String account, String movie, String cinema, String area, int price,
			LocalDate onDate, String time, String seat,String buycode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("starlightmoviecinema@gmail.com");
            message.setTo(userEmail);
            message.setSubject("訂票成功通知");
            
            // 構建付款頁面URL，將所有訂票相關資訊作為參數添加到URL中
            String paymentPageUrl = "http://localhost:5173/paypage?" +
            						"account=" + URLEncoder.encode(account, StandardCharsets.UTF_8) +
                                    "&movie=" + URLEncoder.encode(movie, StandardCharsets.UTF_8) +
                                    "&cinema=" + URLEncoder.encode(cinema, StandardCharsets.UTF_8) +
                                    "&area=" + URLEncoder.encode(area, StandardCharsets.UTF_8) +
                                    "&price=" + price +
                                    "&onDate=" + onDate.toString() +
                                    "&time=" + URLEncoder.encode(time, StandardCharsets.UTF_8) +
                                    "&seat=" + URLEncoder.encode(seat, StandardCharsets.UTF_8) ;

            // 邮件内容
            String emailContent = "您的訂票詳情如下：\n" +
            		"訂購帳號：" + account + "\n" +
                    "時間：" + onDate.toString() + "，" + time + "\n" +
                    "電影：" + movie + "\n" +
                    "影院：" + cinema + "\n" +
                    "影廳：" + area + "\n" +
                    "價格：" + price + "元\n" +
                    "座位：" + seat + "\n\n" +
                    "您可以點擊下方鏈接進行付款：\n" +
                    paymentPageUrl + "\n\n" +
                    "感謝您的訂購，祝您觀影愉快！";

            message.setText(emailContent);

            javaMailSender.send(message);

            System.out.println("訂票成功通知郵件已成功發送。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	private String generateVerificationCode() {
	    byte[] randomBytes = new byte[16];
	    new SecureRandom().nextBytes(randomBytes);
	    return Base64.getEncoder().encodeToString(randomBytes);
	}

}
