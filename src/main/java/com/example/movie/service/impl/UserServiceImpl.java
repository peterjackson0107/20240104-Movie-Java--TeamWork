package com.example.movie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.User;
import com.example.movie.repository.UserDAO;
import com.example.movie.service.ifs.UserService;
import com.example.movie.vo.UserLoginGetRes;
import com.example.movie.vo.UserLoginRes;

import java.util.Optional;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	private final JavaMailSender javaMailSender;
	
    @Autowired
    private UserDAO userDao;
    
    @Autowired
    public UserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public UserLoginRes login(String account, String pwd) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        Optional<User> op = userDao.findById(account);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        User user = op.get();
        if (!encoder.matches(pwd, user.getPwd())) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        if (!user.isVerify()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_VERIFY.getCode(),RtnCode.ACCOUNT_NOT_VERIFY.getMessage());
        }
        if (user.isAdminConfirm()) {
            return new UserLoginRes(RtnCode.SUCCESSFUL_ADMIN_LOGIN.getCode(),RtnCode.SUCCESSFUL_ADMIN_LOGIN.getMessage());
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
        //成功
    }
    

    @Override
    public UserLoginGetRes logincheck(String account) {
        if (!StringUtils.hasText(account)) {
            return (UserLoginGetRes) new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        Optional<User> op = userDao.findById(account);
        if (op.isEmpty()) {
            return (UserLoginGetRes) new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        User user = op.get();
        if (!user.isVerify()) {
            return (UserLoginGetRes) new UserLoginRes(RtnCode.ACCOUNT_NOT_VERIFY.getCode(),RtnCode.ACCOUNT_NOT_VERIFY.getMessage());
        }
        if (user.isAdminConfirm()) {
            return new UserLoginGetRes(RtnCode.SUCCESSFUL_ADMIN_LOGIN.getCode(),RtnCode.SUCCESSFUL_ADMIN_LOGIN.getMessage(),op);
        }
        return new UserLoginGetRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage(),op);
        //成功
    }

    @Override
    public UserLoginRes create(String account, String pwd, String email,int phone, String name) {
    	if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        if (userDao.existsById(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_EXISTED.getCode(),RtnCode.ACCOUNT_EXISTED.getMessage());
        }
        String encodedPwd = encoder.encode(pwd);

        User newUser = new User();
        newUser.setAccount(account);
        newUser.setPwd(encodedPwd);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setName(name);
        newUser.setAdminConfirm(false);
        
        // 生成並保存驗證碼
        String verificationCode = generateVerificationCode();
        newUser.setVerifyCode(verificationCode);
        newUser.setVerify(false);

        // 發送帶有驗證碼的郵件
        sendVerificationEmail(email, verificationCode);
        
        userDao.save(newUser);

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
    @Override
    public UserLoginRes createAdmi(String account, String pwd, String email,int phone, String name) {
    	if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        if (userDao.existsById(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_EXISTED.getCode(),RtnCode.ACCOUNT_EXISTED.getMessage());
        }
        String encodedPwd = encoder.encode(pwd);

        User newUser = new User();
        newUser.setAccount(account);
        newUser.setPwd(encodedPwd);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setName(name);
        newUser.setAdminConfirm(true);
        // 生成並保存驗證碼
        String verificationCode = generateVerificationCode();
        newUser.setVerifyCode(verificationCode);
        newUser.setVerify(true);
        
        userDao.save(newUser);

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
    @Override
    public UserLoginRes verifyAccount(String account, String verificationCode) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(verificationCode)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
        }

        Optional<User> op = userDao.findByAccount(account);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(), RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }

        User user = op.get();
        if (user.isVerify()) {
            return new UserLoginRes(RtnCode.ACCOUNT_ALREADY_VERIFIED.getCode(), RtnCode.ACCOUNT_ALREADY_VERIFIED.getMessage());
        }

        if (verificationCode.equals(user.getVerifyCode())) {
            user.setVerify(true);
            userDao.save(user);

            return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
        } else {
            return new UserLoginRes(RtnCode.VERIFICATION_CODE_INCORRECT.getCode(), RtnCode.VERIFICATION_CODE_INCORRECT.getMessage());
        }
    }

	@Override
	public UserLoginRes updatepwd(String account,String pwd,String newPwd) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        Optional<User> op = userDao.findById(account);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        User user = op.get();
        if (!encoder.matches(pwd, user.getPwd())) {
            return new UserLoginRes(RtnCode.PASSWORD_NOT_FIT.getCode(),RtnCode.PASSWORD_NOT_FIT.getMessage());
        } else {
        	String encodedPwd = encoder.encode(newPwd);
        	user.setPwd(encodedPwd);
        	userDao.save(user);
        	
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}
	
	@Override
	public UserLoginRes update(String account, String pwd, String email, int phone, String name) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(pwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        Optional<User> op = userDao.findById(account);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        User user = op.get();
        if (!encoder.matches(pwd, user.getPwd())) {
        	return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        } else {
        	user.setName(name);
        	user.setEmail(email);
        	user.setPhone(phone);
        	userDao.save(user);
        	
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}
	
	@Override
	public UserLoginRes search(String account) {
        if (!StringUtils.hasText(account)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        boolean op = userDao.findAllByAccount(account);
        if (op) {
            return new UserLoginRes(RtnCode.ACCOUNT_EXISTED.getCode(),RtnCode.ACCOUNT_EXISTED.getMessage());
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}
	
	
    @Override
    public void sendVerificationEmail(String userEmail, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("starlightmoviecinema@gmail.com");
            message.setTo(userEmail);
            message.setSubject("帳號驗證碼");
            String emailContent = "你的驗證碼為：" + verificationCode;
            message.setText(emailContent);

            javaMailSender.send(message);

            System.out.println("Verification email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public UserLoginRes fogetpwd(String email) {
    	if (!StringUtils.hasText(email)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
    	Optional<User> op = userDao.findAllByEmail(email);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(), RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        User user = op.get();
        
        String account = user.getAccount();
        
        // 生成並保存驗證碼
        String verificationCode = generateVerificationCode();
        user.setVerifyCode(verificationCode);
        user.setVerify(false);

        // 發送帶有驗證碼的郵件
        sendVerificationEmailforpwd(account,email, verificationCode);
        
        userDao.save(user);

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
    @Override
    public void sendVerificationEmailforpwd(String account,String userEmail, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("starlightmoviecinema@gmail.com");
            message.setTo(userEmail);
            message.setSubject("修改驗證碼");
            String emailContent = "你的帳號為：" + account + "，" + "你的驗證碼為：" + verificationCode;
            message.setText(emailContent);

            javaMailSender.send(message);

            System.out.println("Verification email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public UserLoginRes verifyforgetAccount(String account,String newPwd, String verificationCode) {
        if (!StringUtils.hasText(account) || !StringUtils.hasText(verificationCode) || !StringUtils.hasText(newPwd)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
        }

        Optional<User> op = userDao.findByAccount(account);
        if (op.isEmpty()) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(), RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }

        User user = op.get();
        if (user.isVerify()) {
            return new UserLoginRes(RtnCode.ACCOUNT_ALREADY_VERIFIED.getCode(), RtnCode.ACCOUNT_ALREADY_VERIFIED.getMessage());
        }

        if (verificationCode.equals(user.getVerifyCode())) {
        	String encodedPwd = encoder.encode(newPwd);
        	user.setPwd(encodedPwd);
            user.setVerify(true);
            userDao.save(user);

            return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
        } else {
            return new UserLoginRes(RtnCode.VERIFICATION_CODE_INCORRECT.getCode(), RtnCode.VERIFICATION_CODE_INCORRECT.getMessage());
        }
    }
	
	private String generateVerificationCode() {
	    byte[] randomBytes = new byte[16];
	    new SecureRandom().nextBytes(randomBytes);
	    return Base64.getEncoder().encodeToString(randomBytes);
	}


}
