package com.example.movie.service.ifs;

import com.example.movie.vo.UserLoginGetRes;
import com.example.movie.vo.UserLoginRes;

public interface UserService {
	
	public UserLoginRes login(String account,String pwd);
	
	public UserLoginGetRes logincheck(String account);
	
	public UserLoginRes create(String account,String pwd,String email,int phone, String name);
	
	public UserLoginRes createAdmi(String account,String pwd,String email,int phone, String name);
	
	public UserLoginRes updatepwd(String account,String pwd,String newPwd);
	
	public UserLoginRes update(String account,String pwd,String email,int phone, String name);
	
	public UserLoginRes search(String account);
	
	public UserLoginRes fogetpwd(String email);
	
	public UserLoginRes verifyAccount(String account, String verificationCode);
	
	void sendVerificationEmailforpwd(String account,String userEmail, String verificationCode);
	
	public UserLoginRes verifyforgetAccount(String account,String newPwd, String verificationCode);
	
	void sendVerificationEmail(String userEmail, String verificationCode);

}
