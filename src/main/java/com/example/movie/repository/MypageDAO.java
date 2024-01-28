package com.example.movie.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Mypage;

@Repository
public interface MypageDAO extends JpaRepository<Mypage, String>{
	
	public Mypage findByAccount(String account);
	
	public @NonNull List<Mypage> findAll();

	
}
