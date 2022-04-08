package it.rea.videoRental.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rea.videoRental.entity.Film;
import it.rea.videoRental.entity.Inventory;
import it.rea.videoRental.entity.Language;
import it.rea.videoRental.entity.Store;
import it.rea.videoRental.payload.request.FilmRequest;
import it.rea.videoRental.payload.response.ActorsFilm;
import it.rea.videoRental.payload.response.FilmMaxRentResponse;
import it.rea.videoRental.payload.response.FilmResponse;
import it.rea.videoRental.payload.response.FilmStoreResponse;
import it.rea.videoRental.repository.InventoryRepository;
import it.rea.videoRental.service.FilmService;
import it.rea.videoRental.service.GenreService;
import it.rea.videoRental.service.LanguageService;
import it.rea.videoRental.service.StoreService;

@RestController
public class FilmController {
	
	@Autowired FilmService filmService;
	@Autowired LanguageService languageService;
	@Autowired GenreService genreService;
	@Autowired StoreService storeService;
	@Autowired InventoryRepository inventoryRepository;
	
	// Controller 01
	/**
	 * <p>Create Entity Film from a request body. Validation needed.
	 * </p>
	 * @param filmRequest DTO for Entity Film
	 * @return filmTitle and httpStatus 200 if creation success. httpStatus for errors
	 */
	@PostMapping("/add-film")
	public ResponseEntity<?> addFilm (@Valid @RequestBody FilmRequest filmRequest){
		// check Language from filmRequest.  If not interrupt method execution  httpStatus 404
		if( !languageService.findLanguageById(filmRequest.getLanguageId()).isPresent() )
			return new ResponseEntity<String>("No language found associated with the given id", 
					HttpStatus.NOT_FOUND);
		// check Genre from filmRequest.  If not interrupt method execution  httpStatus 404
		if ( !genreService.getGenreById(filmRequest.getGenreId()).isPresent() )
			return new ResponseEntity<String>("No genre found associated with the given id",
					HttpStatus.NOT_FOUND);
		// Create and save new Entity Film.  If not interrupt method execution  httpStatus 404
		filmService.saveFilm(
				new Film(
				filmRequest.getTitle(), 
				filmRequest.getDescription(), 
				filmRequest.getReleaseYear(), 
				languageService.findLanguageById(filmRequest.getLanguageId()).get(),
				genreService.getGenreById(filmRequest.getGenreId()).get()
				));
		// return msg and httpStatus
		return new ResponseEntity<String>(filmRequest.getTitle() + " added to your list", HttpStatus.OK);
	}
	
	// Controller 02
	/**
	 * <p> Update Film by the given id
	 * </p>
	 * @param filmRequest DTO for Entity Film
	 * @param filmId long
	 * @return String and httpStatus 200 if correctly update or httpStatus for errors
	 */
	@PutMapping("/update-film/{filmId}")
	public ResponseEntity<?> upateFilm (@PathVariable long filmId, @RequestBody FilmRequest filmRequest){
		// check film is present by given id.  If not interrupt method execution
		Optional<Film> f = filmService.getFilmById(filmId);
		if( !f.isPresent() )
			return new ResponseEntity<String>("No film found for given id", HttpStatus.NOT_FOUND);
		// check Language from filmReques. If not interrupt method execution  httpStatus 404
		if( !languageService.findLanguageById(filmRequest.getLanguageId()).isPresent() )
			return new ResponseEntity<String>("No language found associated with the given id", 
					HttpStatus.NOT_FOUND);
		// check Genre from filmRequest. If not interrupt method execution  httpStatus 404
		if ( !genreService.getGenreById(filmRequest.getGenreId()).isPresent() )
			return new ResponseEntity<String>("No genre found associated with the given id",
					HttpStatus.NOT_FOUND);
		// set Film new info
		f.get().setTitle(filmRequest.getTitle());
		f.get().setDescription(filmRequest.getDescription());
		f.get().setReleaseYear(filmRequest.getReleaseYear());
		f.get().setLanguageId(languageService.findLanguageById(filmRequest.getLanguageId()).get());
		f.get().setGenreId(genreService.getGenreById(filmRequest.getGenreId()).get());
		// update film entity than return msg and httpStatus
		filmService.saveFilm(f.get());
		return new ResponseEntity<String>("Movie " + f.get().getFilmId() + " updated", HttpStatus.OK);
	}
	
	// Controller 03
	/**
	 * <p>Get film info by id
	 * </p>
	 * @param filmId long 
	 * @return filmResponse list if exists {@link it.rea.videoRental.payload.response.FilmResponse}
	 * or httpStatus for errors
	 */
	@GetMapping("/get-film/{filmId}") 
	public ResponseEntity<?> getFilmInfo (@PathVariable long filmId){
		// check film by the given id. If not interrupt method execution and return httpStatus 404
		Optional<FilmResponse> f = filmService.getFilmResponseById(filmId);
		if( !f.isPresent() )
			return new ResponseEntity<String>("No film found for given id", HttpStatus.NOT_FOUND);
		// return filmResponse and httpStatus
		return new ResponseEntity<FilmResponse>(f.get(), HttpStatus.OK);
	}
	
	// controller 04
	/**
	 * <p>Get store name and film info by the given filmId
	 * </p>
	 * @param filmId long 
	 * @return filmStoreResponse list if exists {@link it.rea.videoRental.payload.response.FilmStoreResponse} 
	 * or httpStatus for errors
	 */
	@GetMapping("/find-film-in-store/{filmId}") 
	public ResponseEntity<?> getFilmStoreResponse (@PathVariable long filmId){
		// check film by the given id. If not interrupt method execution and return httpStatus 404
		Optional<Film> f = filmService.getFilmById(filmId);
		if ( !f.isPresent() )
			return new ResponseEntity<String>("No film found for given id", HttpStatus.NOT_FOUND);
		// return filmStoreResponse and httpStatus		
		return new ResponseEntity<List<FilmStoreResponse>>(filmService.getFilmStoreResponse(filmId), 
				HttpStatus.OK);
	}
	
	// controller 05
	/**
	 * <p>Get film info passing a collection of actors surnames
	 * </p>
	 * @param actorsSurnames Collection of string
	 * @return filmResponse list if exists {@link it.rea.videoRental.payload.response.FilmResponse}
	 * or httpStatus for errors
	 */
	@GetMapping("/find-films-by-actors")
	public ResponseEntity<?> getFilmsByActors(@RequestParam Collection<String> actorsSurnames){
		// generate List of films played by actors
		List<ActorsFilm> filmsByActors = filmService.getFilmsFromActors(actorsSurnames);
		// extract actorsIds duplicate values only
		List<Long> filmIdsByCast = new ArrayList<Long>();
		for(int i = 0; i < filmsByActors.size(); i++) {
			for(int j = i+1; j < filmsByActors.size(); j++) {
				if(filmsByActors.get(i).getFilmId() == filmsByActors.get(j).getFilmId()) {
					filmIdsByCast.add(filmsByActors.get(j).getFilmId());
				}
			}
		}
		// return filmResponse by getFilmsById method passing filmIdsByCast List<Long>
		return new ResponseEntity<List<FilmResponse>>(filmService.getFilmsByIds(filmIdsByCast),
				HttpStatus.OK);
	}
	
	// controller 06
	/**
	 * <p>Find film by given language
	 * </p>
	 * @param languageId long
	 * @return filmResponse list if exists {@link it.rea.videoRental.payload.response.FilmResponse}
	 */
	@GetMapping("/find-films-by-language/{languageId}") 
	public ResponseEntity<?> getFilmByLang (@PathVariable long languageId){
		// check language by the given id. If not interrupt method execution and return httpStatus 404
		Optional<Language> l = languageService.findLanguageById(languageId);
		if( !l.isPresent() )
			return new ResponseEntity<String>("No language found for given id", HttpStatus.NOT_FOUND);
		// return filmResponse by getFilmByLanguage method passing language id long
		return new ResponseEntity<List<FilmResponse>>(filmService.getFilmByLanguageId(languageId), 
				HttpStatus.OK);
	}

	// controller 07
	/**
	 * <p>Add film entity to store by the given ids
	 * </p>
	 * @param storeId long
	 * @param filmId long
	 * @return String and httpStatus 200 if correctly update or httpStatus for errors
	 */
	@PostMapping("/add-film-to-store/{storeId}/{filmId}")
	public ResponseEntity<?> addFilmToStore(@PathVariable long storeId, @PathVariable long filmId){
		// check store by the given id. If not interrupt method execution and return httpStatus 404 
		Optional<Store> s = storeService.getStoreById(storeId);
		if( !s.isPresent() )
			return new ResponseEntity<String>("No store found for given id", HttpStatus.NOT_FOUND);
		// check film by the given id. If not interrupt method execution and return httpStatus 404 
		Optional<Film> f = filmService.getFilmById(filmId);
		if( !f.isPresent() )
			return new ResponseEntity<String>("No film found for the given id", HttpStatus.NOT_FOUND);
		// create new inventory and set properties
		Inventory i = new Inventory();
		i.setStoreId(s.get());
		i.setFilmId(f.get());
		// save entity inventory
		inventoryRepository.save(i);
		// return msg and httpStatus
		return new ResponseEntity<String>("Film " + f.get().getTitle() + " added to store " +
				s.get().getStoreName(), HttpStatus.OK);
	}
	
	// controller 12
	/**
	 * <p>Find film with the max number of rent</p>
	 * @return FilmMaxRentResponse
	 */
	@GetMapping("/find-film-with-max-number-of-rent")
	public ResponseEntity<?> maxRentalFilms (){
		// run method and return response
		return new ResponseEntity<List<FilmMaxRentResponse>>(filmService.getFilmsMaxRental(), 
				HttpStatus.OK);
	}
		
}
