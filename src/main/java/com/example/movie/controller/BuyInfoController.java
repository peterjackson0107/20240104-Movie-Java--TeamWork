package com.example.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.BuyInfoService;
import com.example.movie.vo.BuyInfoReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class BuyInfoController {
	
	@Autowired
	private BuyInfoService buyInfoService;
	

	@PostMapping(value = "movie/buyinfo/create")
	public UserLoginRes create(@RequestBody BuyInfoReq req) {
		return buyInfoService.create(req.getAccount(),req.getMovie(),req.getMovieId(),req.getCinema(),req.getArea(),req.getPrice(),
				req.getOnDate(),req.getOnTime(),req.getSeat(),req.isConfirmpay());
	}
	
	@PostMapping(value = "movie/buyinfo/update")
	public UserLoginRes update(@RequestBody BuyInfoReq req) {
				return buyInfoService.update(req.getNumber(),req.getAccount(),req.getMovie(),req.getMovieId(),req.getCinema(),req.getArea(),req.getPrice(),
						req.getOnDate(),req.getOnTime(),req.getSeat(),req.isConfirmpay());
	}
	
	@PostMapping(value = "movie/buyinfo/delete")
	public UserLoginRes delete(@RequestBody BuyInfoReq req) {
		return buyInfoService.delete(req.getNumber());
	}
	
	@PostMapping(value = "movie/buyinfo/paycheck")
	public UserLoginRes paycheck(@RequestBody BuyInfoReq req) {
		return buyInfoService.paycheck(req.getNumber());
	}
	
	@PostMapping(value = "movie/buyinfo/search")
	public UserLoginRes search(@RequestBody BuyInfoReq req) {
				return buyInfoService.search(req.getAccount());
	}
	
	@PostMapping(value = "movie/buyinfo/searchP")
	public UserLoginRes searchP(@RequestBody BuyInfoReq req) {
				return buyInfoService.searchP(req.getMovieId(),req.getCinema());
	}
	
	@PostMapping(value = "movie/buyinfo/searchseat")
	public UserLoginRes searchseat(@RequestBody BuyInfoReq req) {
				return buyInfoService.searchseat(req.getMovieId(),req.getCinema(),req.getArea(),req.getOnDate(),req.getOnTime());
	}

}
