package it.rea.videoRental.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.FilmStaff;
import it.rea.videoRental.entity.composite_key.FilmStaffId;
import it.rea.videoRental.payload.response.ActorGenreResponse;
import it.rea.videoRental.payload.response.ActorsFilm;
import it.rea.videoRental.payload.response.FilmResponse;

@Repository
public interface FilmStaffRepository extends JpaRepository<FilmStaff, FilmStaffId> {

	/**
	 * <p>This query get film and actor info from staff id list limited by film genre</p>
	 * @param staffIds Collection of long
	 * @param genre String
	 * @return ActorGenreResponse list {@link it.rea.videoRental.payload.response.ActorGenreResponse}
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.ActorGenreResponse("
			+ "f.filmStaffId.filmId.filmId, "
			+ "f.filmStaffId.filmId.title, "
			+ "f.filmStaffId.staffId.firstname, "
			+ "f.filmStaffId.staffId.lastname, "
			+ "f.filmStaffId.filmId.genreId.genreName"
			+ ") "
			+ "FROM FilmStaff f "
			+ "WHERE f.filmStaffId.staffId.staffId IN :staffIds "
			+ "AND f.filmStaffId.filmId.genreId.genreName = :genre "
			+ "AND f.filmStaffId.roleId.roleId = 1")
	List<ActorGenreResponse> getFilmsByActorsAndGenre(@Param("staffIds") Collection<Long> staffIds, @Param("genre") String genre);
	
	/**
	 * <p>This query get film info by the given film id collection</p>
	 * @param filmIds Collection of long
	 * @return FilmResponse list {@link it.rea.videoRental.payload.response.FilmResponse}
	 */
	@Query(value = "SELECT DISTINCT new it.cgmconsulting.examDroetti.payload.response.FilmResponse("
			+ "f.filmStaffId.filmId.filmId, "
			+ "f.filmStaffId.filmId.title, "
			+ "f.filmStaffId.filmId.description, "
			+ "f.filmStaffId.filmId.releaseYear, "
			+ "f.filmStaffId.filmId.languageId.languageName"
			+ ") "
			+ "FROM FilmStaff f "
			+ "WHERE f.filmStaffId.filmId.filmId IN :filmIds "
			+ "ORDER BY f.filmStaffId.filmId.title")	
	List<FilmResponse> getFilmsByIds(@Param("filmIds") Collection<Long> filmIds);
	
	/**
	 * <p>This query get staff id and associated film id from FilmStaff Entity where staff is an actor</p>
	 * @param actorsSurnames
	 * @return ActorsFilm list {@link it.rea.videoRental.payload.response.ActorsFilm}
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.ActorsFilm("
			+ "f.filmStaffId.filmId.filmId, "
			+ "f.filmStaffId.staffId.staffId"
			+ ")"
			+ "FROM FilmStaff f "
			+ "WHERE f.filmStaffId.staffId.lastname IN :actors "
			+ "AND f.filmStaffId.roleId.roleId = 1")
	List<ActorsFilm> getFilmsFromActors(@Param("actors") Collection<String> actorsSurnames);

}
