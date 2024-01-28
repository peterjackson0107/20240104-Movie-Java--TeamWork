package com.example.movie.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, String>{
	
	@Transactional
	public int deleteByCommentIndexAndCommentIndexIndexAndMovieID(int commentIndex,int commentIndexIndex, int movieID);
	
	public Optional<Comment> findByCommentIndexAndCommentIndexIndexAndMovieID(int commentIndex,int commentIndexIndex, int movieID);
	
	public Optional<Comment> findTopByMovieIDOrderByCommentIndexDesc(int movieID);
	
	public Optional<Comment> findTopByMovieIDAndCommentIndexOrderByCommentIndexIndexDesc(int movieID, int commentIndex);
	
	public List<Comment> findAllByMovieID(int movieID);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.commentIndex = :commentIndex AND c.movieID = :movieID")
	public int deleteAllByCommentIndexAndMovieID(@Param("commentIndex") int commentIndex, @Param("movieID") int movieID);
	
}
