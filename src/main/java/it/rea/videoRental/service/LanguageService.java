package it.rea.videoRental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Language;
import it.rea.videoRental.repository.LanguageRepository;

@Service
public class LanguageService {
	
	@Autowired LanguageRepository languageRepository;
	
	/**
	 * <p>Method related to jpa keywork findById
	 * </p>
	 * @param id long
	 * @return optional Language entity
	 */
	public Optional<Language> findLanguageById(long id){
		return languageRepository.findById(id);
	}

}
