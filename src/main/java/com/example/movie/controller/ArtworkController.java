package com.example.movie.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.service.ifs.ArtworkService;
import com.example.movie.service.ifs.CommentService;
import com.example.movie.vo.ArtworkReq;
import com.example.movie.vo.CommentReq;
import com.example.movie.vo.UserLoginRes;

@CrossOrigin(origins = "*")
@RestController
public class ArtworkController {
	
	@Autowired
	private ArtworkService artworkService;
	

	@PostMapping(value = "movie/art/create")
	public UserLoginRes create(@RequestBody ArtworkReq req) {
		return artworkService.create(req.getMovie(),req.getMovieId(),req.getAccount(),req.getArtname(),req.getArtlocation());
	}
	
	@PostMapping(value = "movie/art/update")
	public UserLoginRes update(@RequestBody ArtworkReq req) {
				return artworkService.update(req.getArtorder(),req.getArtname(),req.getArtlocation());
	}
	
	@PostMapping(value = "movie/art/delete")
	public UserLoginRes delete(@RequestBody ArtworkReq req) {
		return artworkService.delete(req.getArtorder());
	}
	
	@PostMapping(value = "movie/art/search")
	public UserLoginRes search(@RequestBody ArtworkReq req) {
				return artworkService.search(req.getMovie(),req.getMovieId(),req.getArtname());
	}

}
