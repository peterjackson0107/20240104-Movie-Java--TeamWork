package com.example.movie.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Artwork;

@Repository
public interface ArtworkDAO extends JpaRepository<Artwork, String>{
	
    public List<Artwork> findByMovieIdContainingAndArtNameContaining(String movieId, String artname);
    
    public Optional<Artwork> findByArtOrder(int artorder);
    
	@Transactional
	public int deleteByArtOrder(int artorder);

}
