package com.example.movie.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.MovieInfo;
import com.example.movie.repository.MovieInfoDAO;
import com.example.movie.service.ifs.MovieInfoService;
import com.example.movie.vo.MovieInfoGetRes;
import com.example.movie.vo.UserLoginRes;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {
	
    @Autowired
    private MovieInfoDAO movieInfoDao;

    @Override
    public UserLoginRes create(String movieId,String movie, String cinema, String area, int price,
			LocalDate onDate, String time,boolean onSell) {
        if (!StringUtils.hasText(movieId)) {
            return new UserLoginRes(RtnCode.CHECK_MOVIE_INPUT.getCode(),RtnCode.CHECK_MOVIE_INPUT.getMessage());
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

        movieInfoDao.save(new MovieInfo(movieId,movie,cinema,area,price,onDate,time,onSell));
        
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
	@Override
	public UserLoginRes update(int number,String movieId,String movie, String cinema, String area, int price,
			LocalDate onDate, String time,boolean onSell) {
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
        Optional<MovieInfo> op = movieInfoDao.findByNumber(number);
        MovieInfo movieinfo = op.get();
		try {
			movieinfo.setMovie(movie);
			movieinfo.setCinema(cinema);
			movieinfo.setArea(area);
			movieinfo.setPrice(price);
			movieinfo.setOnDate(onDate);
			movieinfo.setOnTime(time);
			movieinfo.setOnSell(onSell);
			movieInfoDao.save(movieinfo);
//			movieInfoDao.updateInfo(order,movie,cinema,area,price,startDate,endDate,onDate,time);
		} catch (Exception e) {
			return new UserLoginRes(RtnCode.MOVIE_INFO_SAVE_ERROR.getCode(), RtnCode.MOVIE_INFO_SAVE_ERROR.getMessage());
		}
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}


	@Override
	public UserLoginRes delete(int number) {
		int res = movieInfoDao.deleteByNumber(number);
		if(res == 0) {
			return new UserLoginRes(RtnCode.DELETED_MOVIE_INFO_NOT_EXSISTED.getCode(), RtnCode.DELETED_MOVIE_INFO_NOT_EXSISTED.getMessage());
		}else {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		}
	}

	@Override
	public UserLoginRes search(String movieId,String movie, String cinema, String area, 
			LocalDate startDate, LocalDate endDate) {
		movieId = !StringUtils.hasText(movieId) ? "" : movieId;
		movie = !StringUtils.hasText(movie) ? "" : movie;
		cinema = !StringUtils.hasText(cinema) ? "" : cinema;
		area = !StringUtils.hasText(area) ? "" : area;
		startDate = startDate== null ? LocalDate.of(1970,01,01) : startDate;
		endDate = endDate == null ? LocalDate.of(2099,12,31) : endDate;
		List<MovieInfo> res = new ArrayList<>();
			res = movieInfoDao.findByMovieIdContainingAndCinemaContainingAndOnDateBetween(movieId,cinema,startDate,endDate);
				return new MovieInfoGetRes(RtnCode.SUCCESSFUL.getCode(),
						RtnCode.SUCCESSFUL.getMessage(),res);

		
	}

}
