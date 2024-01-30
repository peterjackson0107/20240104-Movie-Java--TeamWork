package com.example.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.ArtworkService;
import com.example.movie.service.ifs.MovieInfoService;
import com.example.movie.vo.ArtworkReq;
import com.example.movie.vo.MovieInfoReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class MovieInfoController {
	
	@Autowired
	private MovieInfoService movieInfoService;
	

	@PostMapping(value = "movie/movieinfo/create")
	public UserLoginRes create(@RequestBody MovieInfoReq req) {
		return movieInfoService.create(req.getMovieId(),req.getMovie(),req.getCinema(),req.getArea(),req.getPrice(),
				req.getOnDate(),req.getOnTime(),req.isOnSell());
	}
	
	@PostMapping(value = "movie/movieinfo/update")
	public UserLoginRes update(@RequestBody MovieInfoReq req) {
				return movieInfoService.update(req.getNumber(),req.getMovieId(),req.getMovie(),req.getCinema(),req.getArea(),
						req.getPrice(),req.getOnDate(),req.getOnTime(),req.isOnSell());
	}
	
	@PostMapping(value = "movie/movieinfo/delete")
	public UserLoginRes delete(@RequestBody MovieInfoReq req) {
		return movieInfoService.delete(req.getNumber());
	}
	
	@PostMapping(value = "movie/movieinfo/search")
	public UserLoginRes search(@RequestBody MovieInfoReq req) {
				return movieInfoService.search(req.getMovieId(),req.getMovie(),req.getCinema(),
						req.getArea(),req.getStartDate(),req.getEndDate());
	}

}
