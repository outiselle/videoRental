package it.rea.videoRental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
	
	Optional<Genre> findByGenreId(long id);
	Optional<Genre> findByGenreName(String genre);

}
