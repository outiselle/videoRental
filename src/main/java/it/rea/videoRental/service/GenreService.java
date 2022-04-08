package it.rea.videoRental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Genre;
import it.rea.videoRental.repository.GenreRepository;

@Service
public class GenreService {

	@Autowired GenreRepository genreRepository;
	
	/**
	 * <p>Service related to finById query 
	 * </p>
	 * @param id long
	 * @return genre entity
	 */
	public Optional<Genre> getGenreById(long id){
		return genreRepository.findById(id);
	}
	
	public Optional<Genre> getGenreByName(String genre){
		return genreRepository.findByGenreName(genre);
	}
}
