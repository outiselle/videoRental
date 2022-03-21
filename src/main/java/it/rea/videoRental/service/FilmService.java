package it.rea.videoRental.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Film;
import it.rea.videoRental.payload.response.ActorsFilm;
import it.rea.videoRental.payload.response.FilmMaxRentResponse;
import it.rea.videoRental.payload.response.FilmResponse;
import it.rea.videoRental.payload.response.FilmStoreResponse;
import it.rea.videoRental.repository.FilmRepository;
import it.rea.videoRental.repository.FilmStaffRepository;
import it.rea.videoRental.repository.InventoryRepository;
import it.rea.videoRental.repository.RentalRepository;

@Service
public class FilmService {

	@Autowired FilmRepository filmRepository;
	@Autowired InventoryRepository inventoryRepository;
	@Autowired RentalRepository rentalRepository;
	@Autowired FilmStaffRepository filmStaffRepository;
	
	/**
	 * <p>Method relatd to jpa keyword save Film Entity</p>
	 * @param film Entity
	 */
	public void saveFilm (Film film) {
		filmRepository.save(film);
	}
	
	/**
	 * <p>Service related to fiByFilmId query {@link it.rea.videoRental.repository.FilmRepository#findByFilmId(long)}
	 * </p>
	 * @param id long
	 * @return optional Film Entity
	 */
	public Optional<Film> getFilmById(long id){
		return filmRepository.findByFilmId(id);
	}
	
	/**
	 * <p>Service related to getFilmResponse query {@link it.rea.videoRental.repository.FilmRepository#getFilmResponse(long)}
	 * </p>
	 * @param id
	 * @return optional FilmResponse
	 */
	public Optional<FilmResponse> getFilmResponseById(long id){
		return filmRepository.getFilmResponse(id);
	}
	
	/**
	 * <p>Method related to getFilmStoreResponse query {@link it.rea.videoRental.repository.InventoryRepository#getFilmStoreResponse(long)}
	 * @param id long
	 * @return FilmStoreResponse <List>
	 */
	public List<FilmStoreResponse> getFilmStoreResponse (long id){
		return inventoryRepository.getFilmStoreResponse(id);
	}
	
	/**
	 * <p>Method related to getFilmResponseByLanguage query {@link it.rea.videoRental.repository.FilmRepository#getFilmResponseByLanguage(long)}
	 * @param id long
	 * @return FilmResponse <List>
	 */
	public List<FilmResponse> getFilmByLanguageId(long id) {
		return filmRepository.getFilmResponseByLanguage(id);
	}
	
	
	/**
	 * <p>Service related to getFilmsFilmsById query 
	 * {@link it.rea.videoRental.repository.FilmStaffRepository#getFilmsByIds(Collection)}
	 * </p>
	 * @param filmIds
	 * @return FilmResponse {@link it.rea.videoRental.payload.response.FilmResponse}
	 */
	public List<FilmResponse> getFilmsByIds(Collection<Long> filmIds){
		return filmStaffRepository.getFilmsByIds(filmIds);
	}
	
	/**
	 * <p>Service related to getFilmsFromActors query
	 * {@link it.rea.videoRental.repository.FilmStaffRepository#getFilmsFromActors(Collection)}
	 * </p>
	 * @param actorsSurnames
	 * @return ActorsFilm {@link it.rea.videoRental.payload.response.ActorsFilm}
	 */
	public List<ActorsFilm> getFilmsFromActors(Collection<String> actorsSurnames){
		return filmStaffRepository.getFilmsFromActors(actorsSurnames);
	}
	
	/**
	 * <p>Service related to filmsMaxRent query {@link it.rea.videoRental.repository.FilmRepository#filmsMaxRent()}
	 * Return list order by rental count than take first and others with same rent value
	 * </p>
	 * @return FilmMaxRentResponse <List>
	 */
	public List<FilmMaxRentResponse> getFilmsMaxRental(){
		// execute query, count rentals for all films
		List<FilmMaxRentResponse> tmp = filmRepository.filmsMaxRent();
		// loop the list and looking for entity with the same value of first
		List<FilmMaxRentResponse> result = new ArrayList<FilmMaxRentResponse>();
		for (FilmMaxRentResponse f : tmp) {
			if(f.getTotalRent() == tmp.get(0).getTotalRent()) {
				result.add(f);
			}
		}
		return result;
	} 

	
}
