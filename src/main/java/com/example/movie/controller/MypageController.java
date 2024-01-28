package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.MypageService;
import com.example.movie.vo.MypageReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	

	@PostMapping(value = "movie/mypage/create")
	public UserLoginRes create(@RequestBody MypageReq req) {
		return mypageService.create(req.getAccount(),req.getFavorit(),req.getWatchList(),req.getAccountMovieList(),req.getFavoritComment());
	}
	
	@PostMapping(value = "movie/mypage/update")
	public UserLoginRes update(@RequestBody MypageReq req) {
				return mypageService.update(req.getAccount(),req.getFavorit(),req.getWatchList(),req.getAccountMovieList(),req.getFavoritComment());
	}
	
	//因為有用 @RequestParam ，api的 uri會是movie/mypage/search?account=帳號
	@PostMapping(value = "movie/mypage/search")
	public UserLoginRes search(@RequestParam(value = "account") String account) {
		return mypageService.search(account);
    }
	
	//因為有用 @RequestParam ，api的 uri會是movie/mypage/search?account=帳號
	@PostMapping(value = "movie/mypage/searchA")
	public UserLoginRes searchALL() {
		return mypageService.searchALL();
    }

}
