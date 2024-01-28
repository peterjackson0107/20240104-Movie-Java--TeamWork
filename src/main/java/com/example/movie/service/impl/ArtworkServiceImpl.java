package com.example.movie.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.movie.constant.RtnCode;
import com.example.movie.entity.Artwork;
import com.example.movie.repository.ArtworkDAO;
import com.example.movie.service.ifs.ArtworkService;
import com.example.movie.vo.ArtworkGetRes;
import com.example.movie.vo.UserLoginRes;

@Service
public class ArtworkServiceImpl implements ArtworkService {
	
    @Autowired
    private ArtworkDAO artworkDao;

    @Override
    public UserLoginRes create(String movie,String movieId,String account,String artname,String artLocation) {
        if (!StringUtils.hasText(movie) || !StringUtils.hasText(account) || !StringUtils.hasText(artname) ||
        		!StringUtils.hasText(artLocation)) {
            return new UserLoginRes(RtnCode.PARAM_ERROR.getCode(),RtnCode.PARAM_ERROR.getMessage());
        }
        artworkDao.save(new Artwork(movie,movieId, account, artname, artLocation));
        return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(),RtnCode.SUCCESSFUL.getMessage());
    }

	@Override
	public UserLoginRes update(int artorder,String artname,String artLocation) {
		Optional<Artwork> op = artworkDao.findByArtOrder(artorder);
		if (op.isEmpty()){
			return new UserLoginRes(RtnCode.ART_IS_NOT_FOUND.getCode(),RtnCode.ART_IS_NOT_FOUND.getMessage());
		}
		Artwork comment = op.get();
		if(comment.getArtOrder() != artorder) {
			return new UserLoginRes(RtnCode.ART_IS_NOT_FOUND.getCode(),RtnCode.ART_IS_NOT_FOUND.getMessage());
		}
		try {
			comment.setArtName(artname);
			comment.setArtLocation(artLocation);
			artworkDao.save(comment);
		} catch (Exception e) {
			return new UserLoginRes(RtnCode.PAGE_CREATE_ERROR.getCode(), RtnCode.PAGE_CREATE_ERROR.getMessage());
		}
		return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public UserLoginRes delete(int artorder) {
		int res = artworkDao.deleteByArtOrder(artorder);
		if(res == 1) {
			return new UserLoginRes(RtnCode.SUCCESSFUL.getCode(), RtnCode.SUCCESSFUL.getMessage());
		} else {
			return new UserLoginRes(RtnCode.ART_DLETE_ORDER_NOT_FOUND.getCode(), RtnCode.ART_DLETE_ORDER_NOT_FOUND.getMessage());
		}

	}

	@Override
	public UserLoginRes search(String movie,String movieId,String artname) {
		movie = !StringUtils.hasText(movie) ? "" : movie;
		artname = !StringUtils.hasText(artname) ? "" : artname;
		List<Artwork> res = new ArrayList<>();
			res = artworkDao.findByMovieIdContainingAndArtNameContaining(movieId,artname);
		if(res.isEmpty() || res==null) {
			return new UserLoginRes(RtnCode.ART_IS_NOT_FOUND.getCode(), RtnCode.ART_IS_NOT_FOUND.getMessage());
		}
		 	return new ArtworkGetRes(RtnCode.SUCCESSFUL.getCode(),
					RtnCode.SUCCESSFUL.getMessage(),res);
		}

}
