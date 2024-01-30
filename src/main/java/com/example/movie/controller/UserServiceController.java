package com.example.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.UserService;
import com.example.movie.vo.UserLoginReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	
	//
	@PostMapping(value = "/movie/user/login")
	public UserLoginRes login(@RequestBody UserLoginReq req) {
		return userService.login(req.getAccount(),req.getPwd());
	}
	
	@PostMapping(value = "/movie/user/loginCheck")
	public UserLoginRes logincheck(@RequestBody UserLoginReq req) {
		UserLoginRes res = userService.logincheck(req.getAccount());
		return res;
	}
	
	@PostMapping(value = "movie/user/create")
	public UserLoginRes create(@RequestBody UserLoginReq req) {
		return userService.create(req.getAccount(),req.getPwd(),req.getEmail(),req.getPhone(),req.getName());
	}
	
	@PostMapping(value = "movie/user/createAdmi")
	public UserLoginRes createAdmi(@RequestBody UserLoginReq req) {
		return userService.createAdmi(req.getAccount(),req.getPwd(),req.getEmail(),req.getPhone(),req.getName());
	}
	
	@PostMapping(value = "movie/user/updatepwd")
	public UserLoginRes updatepwd(@RequestBody UserLoginReq req) {
		return userService.updatepwd(req.getAccount(),req.getPwd(),req.getNewPwd());
	}
	
	@PostMapping(value = "movie/user/update")
	public UserLoginRes update(@RequestBody UserLoginReq req) {
		return userService.update(req.getAccount(),req.getPwd(),req.getEmail(),req.getPhone(),req.getName());
	}
	
	@PostMapping(value = "movie/user/search")
	public UserLoginRes search(@RequestBody UserLoginReq req) {
		return userService.search(req.getAccount());
	}
	
	@PostMapping(value = "movie/user/verifyAccount")
	public UserLoginRes verifyAccount(@RequestBody UserLoginReq req) {
		return userService.verifyAccount(req.getAccount(),req.getVerificationCode());
	}
	
	@PostMapping(value = "movie/user/forgetpwd")
	public UserLoginRes fogetpwd(@RequestBody UserLoginReq req) {
		return userService.fogetpwd(req.getEmail());
	}
	
	@PostMapping(value = "movie/user/verifypwdAccount")
	public UserLoginRes verifyforgetAccount(@RequestBody UserLoginReq req) {
		return userService.verifyforgetAccount(req.getAccount(),req.getNewPwd(),req.getVerificationCode());
	}

}
