package it.rea.videoRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
	
	
}
