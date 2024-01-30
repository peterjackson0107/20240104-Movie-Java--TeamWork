package com.example.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Column(name = "name")
	private String name;
	
	@Id
	@Column(name = "account")
	private String account;
	
	@Column(name = "password")
	private String pwd;

	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private int phone;
	
	@Column(name = "verify")
	private boolean verify;
	
	@Column(name = "verify_code")
	private String verifyCode;
	
	@Column(name = "admin_confirm")
	private boolean adminConfirm;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String account, String pwd, String email, int phone, boolean verify, String verifyCode,boolean adminConfirm) {
		super();
		this.name = name;
		this.account = account;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.verify = verify;
		this.verifyCode = verifyCode;
		this.adminConfirm = adminConfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public boolean isAdminConfirm() {
		return adminConfirm;
	}

	public void setAdminConfirm(boolean adminConfirm) {
		this.adminConfirm = adminConfirm;
	}
	
	

}
