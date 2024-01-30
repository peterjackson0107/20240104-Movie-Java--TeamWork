package com.example.movie.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.Comment;
import com.example.movie.repository.CommentDAO;
import com.example.movie.service.ifs.CommentService;
import com.example.movie.vo.CommentGetRes;
import com.example.movie.vo.UserLoginRes;

@Service
public class CommentServiceImpl implements CommentService {
	
    @Autowired
    private CommentDAO commentDao;

    @Override
    public UserLoginRes create(String movie,int movieID,String commentText,String account) {
        if (!StringUtils.hasText(commentText)) {
            return new UserLoginRes(RtnCode.COMMENT_TEXT_IS_NONE.getCode(),RtnCode.COMMENT_TEXT_IS_NONE.getMessage());
        }
        if (!StringUtils.hasText(account)) {
            return new UserLoginRes(RtnCode.ACCOUNT_NOT_FOUND.getCode(),RtnCode.ACCOUNT_NOT_FOUND.getMessage());
        }
        Optional<Comment> op = commentDao.findTopByMovieIDOrderByCommentIndexDesc(movieID);
        Comment comment = null;
        if(op.isEmpty()) {
        	comment = commentDao.save(new Comment(movie,movieID, 1, commentText,account));
        } else {
        	comment = op.get();
            int x = comment.getCommentIndex() + 1 ;
            
            commentDao.save(new Comment(movie,movieID, x, commentText,account));
        }
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }
    
	@Override
	public UserLoginRes createchild(int commentIndex, String movie,int movieID, String commentText,String account) {
        if (!StringUtils.hasText(commentText)) {
            return new UserLoginRes(RtnCode.COMMENT_TEXT_IS_NONE.getCode(),RtnCode.COMMENT_TEXT_IS_NONE.getMessage());
        }
        Optional<Comment> op = commentDao.findTopByMovieIDAndCommentIndexOrderByCommentIndexIndexDesc(movieID,commentIndex);
        Comment comment;
        
        if(op.get().getCommentIndexIndex() != 0) {
        	comment = op.get();
        	int newCommentIndexOrder = comment.getCommentIndexIndex() + 1;
        	commentDao.save(new Comment(movie,movieID, commentIndex, newCommentIndexOrder, commentText,account));
        } else {
        	commentDao.save(new Comment(movie,movieID, commentIndex, 1 , commentText,account));
        }

        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
	}


	@Override
	public UserLoginRes update(int commentIndex,int commentIndexOrder, String movie,int movieID, String commentText,String account) {
		if(commentIndex ==0 || (commentIndex==0 && commentIndexOrder==0) || !StringUtils.hasText(movie) || !StringUtils.hasText(commentText) || !StringUtils.hasText(account)) {
			return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
		}
		Optional<Comment> op = commentDao.findByCommentIndexAndCommentIndexIndexAndMovieID(commentIndex,commentIndexOrder,movieID);
		if (op.isEmpty()){
			return new UserLoginRes(RtnCode.MOVIE_COMMENT_NOT_FOUND.getCode(),RtnCode.MOVIE_COMMENT_NOT_FOUND.getMessage());
		}
		Comment comment = op.get();
		if(comment.getCommentIndex() != commentIndex) {
			return new UserLoginRes(RtnCode.MOVIE_COMMENT_NOT_FOUND.getCode(),RtnCode.MOVIE_COMMENT_NOT_FOUND.getMessage());
		}
		if(StringUtils.hasText(commentText)){
			comment.setCommentText(commentText);
			comment.setCommentTime(LocalDateTime.now());
			comment.setAccount(account);
		}
		try {
			commentDao.save(comment);
		} catch (Exception e) {
			return new UserLoginRes(RtnCode.PAGE_CREATE_ERROR.getCode(), RtnCode.PAGE_CREATE_ERROR.getMessage());
		}
		return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public UserLoginRes deleteF(int commentIndex,int movieID) {
		int res = commentDao.deleteAllByCommentIndexAndMovieID(commentIndex,movieID);
		if(res == 0) {
			return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
		}else {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		}
		
	}
	
	@Override
	public UserLoginRes deleteC(int commentIndex,int commentIndexOrder,int movieID) {
		int res = commentDao.deleteByCommentIndexAndCommentIndexIndexAndMovieID(commentIndex,commentIndexOrder,movieID);
		if(res == 0) {
			return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
		}else {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		}
		
	}

	@Override
	public UserLoginRes likeAndDislike(int commentIndex, int commentIndexOrder,int movieID, int like, int dislike) {
        Optional<Comment> op = commentDao.findByCommentIndexAndCommentIndexIndexAndMovieID(commentIndex,commentIndexOrder,movieID);
        Comment comment = op.get();
        if(comment.getCommentIndex() != commentIndex) {
        	return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
        }
        if(comment.getCommentIndexIndex() !=commentIndexOrder) {
        	return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(), RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
        }
        if(like == 0 && dislike ==0) {
        	return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(), RtnCode.PARAM_ERROR.getMessage());
        }
        
        int likeNew = comment.getFavorite() + like;
        int dislikeNew = comment.getDislike() + dislike;
        comment.setFavorite(likeNew);
        comment.setDislike(dislikeNew);
        commentDao.save(comment);
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public UserLoginRes search(int movieID) {
        if (movieID ==0) {
            return new UserLoginRes(RtnCode.COMMENT_IS_NOT_EXSISTED.getCode(),RtnCode.COMMENT_IS_NOT_EXSISTED.getMessage());
        }
        List<Comment> res = commentDao.findAllByMovieID(movieID);
		return new CommentGetRes(RtnCode.SUCCESSFUL.getCode(),
				RtnCode.SUCCESSFUL.getMessage(),res);
	}

}
