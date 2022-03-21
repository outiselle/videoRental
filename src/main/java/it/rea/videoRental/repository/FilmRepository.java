package it.rea.videoRental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Film;
import it.rea.videoRental.payload.response.FilmMaxRentResponse;
import it.rea.videoRental.payload.response.FilmResponse;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>{
	
	Optional<Film> findByFilmId(long id);
	
	/**
	 * <p>This query get film info for the given id</p>
	 * @param id long
	 * @return optional FilmResponse
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.FilmResponse("
			+ "f.filmId,  "
			+ "f.title,  "
			+ "f.description, "
			+ "f.releaseYear, "
			+ "(SELECT l.languageName FROM Language l WHERE f.languageId=l.languageId) as Language"
			+ ") "
			+ "FROM Film f "
			+ "WHERE f.filmId= :id")
	Optional<FilmResponse> getFilmResponse(@Param("id") long id);
	
	/**
	 * <p>This query get film info and store into FilmResponse by the given language id</p>
	 * @param id long
	 * @return FilmResponse <List>
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.FilmResponse("
			+ "f.filmId, "
			+ "f.title, "
			+ "f.description, "
			+ "f.releaseYear, "
			+ "(SELECT l.languageName FROM Language l WHERE f.languageId=l.languageId) as Language"
			+ ") "
			+ "FROM Film f "
			+ "WHERE f.languageId.languageId= :id")
	List<FilmResponse> getFilmResponseByLanguage(@Param("id") long id);
	
	/**
	 * <p> This query found film id, title and total rent for each film join on Inventory Entity to get film id
	 * and Rental Entity to count rents number
	 * </p>  
	 * @return FilmMaxRentResponse list {@link it.rea.videoRental.payload.response.FilmMaxRentResponse}
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.FilmMaxRentResponse("
			+ "f.filmId, "
			+ "f.title, "
			+ "COUNT (f) as mxcnt"
			+ ")"
			+ "FROM Film f "
			+ "JOIN Inventory i ON f.filmId = i.filmId.filmId "
			+ "JOIN Rental r ON i.inventoryId = r.rentalId.inventoryId "
			+ "GROUP BY f.filmId "
			+ "ORDER BY mxcnt DESC")
	List<FilmMaxRentResponse> filmsMaxRent();
	
}
