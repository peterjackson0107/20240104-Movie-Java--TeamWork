package com.example.movie.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.Mypage;
import com.example.movie.repository.MypageDAO;
import com.example.movie.service.ifs.MypageService;
import com.example.movie.vo.MypageGetRes;
import com.example.movie.vo.UserLoginRes;

@Service
public class MypageServiceImpl implements MypageService {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private MypageDAO mypageDao;

    @Override
    public UserLoginRes create(String account,String favorit,String watchList, String accountMovieList,String favoritComment) {
        if (!StringUtils.hasText(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }

        mypageDao.save(new Mypage(account, favorit, watchList, accountMovieList,favoritComment));

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }

	@Override
	public UserLoginRes update(String account,String favorit,String watchList, String accountMovieList,String favoritComment) {
		Optional<Mypage> op = mypageDao.findById(account);
		if (op.isEmpty()){
			return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
		}
		Mypage mypage = op.get();
		if(StringUtils.hasText(favorit)){
			mypage.setFavorit(favorit);
		}
		if (StringUtils.hasText(watchList)){
			mypage.setWatchList(watchList);
		}
		if (StringUtils.hasText(accountMovieList)){
			mypage.setAccountMovieList(accountMovieList);
		}
		if (StringUtils.hasText(favoritComment)){
			mypage.setFavoritComment(favoritComment);
		}
		try {
			mypageDao.save(mypage);
		} catch (Exception e) {
			return new UserLoginRes(RtnCode.PAGE_CREATE_ERROR.getCode(), RtnCode.PAGE_CREATE_ERROR.getMessage());
		}
		return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public UserLoginRes search(String account) {
		Optional<Mypage> op = mypageDao.findById(account);
		if(op.isEmpty()) {
			return new UserLoginRes(RtnCode.PAGE_NOT_FOUND.getCode(), RtnCode.PAGE_NOT_FOUND.getMessage());
		}
		return new MypageGetRes(RtnCode.SUCCESSFUL.getCode(),
				RtnCode.SUCCESSFUL.getMessage(),op);
	}

	@Override
	public UserLoginRes searchALL() {
		List<Mypage> op = mypageDao.findAll();
		if(op.isEmpty()) {
			return new UserLoginRes(RtnCode.PAGE_NOT_FOUND.getCode(), RtnCode.PAGE_NOT_FOUND.getMessage());
		}
		return new MypageGetRes(RtnCode.SUCCESSFUL.getCode(),
				RtnCode.SUCCESSFUL.getMessage(),op);
	}


}
