package com.example.movie.service.ifs;


import com.example.movie.vo.UserLoginRes;

public interface MypageService {
	
	public UserLoginRes create(String account,String favorit,String watchList, String accountMovieList,String favoritComment);

	public UserLoginRes update(String account, String favorit, String watchList, String accountMovieList,String favoritComment);
	
	public UserLoginRes search(String account);
	
	public UserLoginRes searchALL();

}
