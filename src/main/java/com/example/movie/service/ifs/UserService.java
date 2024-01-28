package com.example.movie.service.ifs;

import com.example.movie.vo.UserLoginRes;

public interface UserService {
	
	public UserLoginRes login(String account,String pwd);
	
	public UserLoginRes create(String account,String pwd,String email,int phone, String name);
	
	public UserLoginRes updatepwd(String account,String pwd,String newPwd);
	
	public UserLoginRes update(String account,String pwd,String email,int phone, String name);
	
	public UserLoginRes search(String account);
	
	public UserLoginRes verifyAccount(String account, String verificationCode);
	
	void sendVerificationEmail(String userEmail, String verificationCode);

}
