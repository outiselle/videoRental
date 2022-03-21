package it.rea.videoRental.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.rea.videoRental.entity.Genre;
import it.rea.videoRental.entity.Staff;
import it.rea.videoRental.payload.request.ActorGenreRequest;
import it.rea.videoRental.payload.response.ActorGenreResponse;
import it.rea.videoRental.service.GenreService;
import it.rea.videoRental.service.StaffService;

@RestController
public class StaffController {
	
	@Autowired StaffService staffService;
	@Autowired GenreService genreService;

	// controller 13
	/**
	 * <p>Method to retrieve film and actor info giving actor surnames and genre name.
	 * If more actors have same surname and act in film with the same genre all of them are included in list
	 * </p>
	 * @param request {@link it.rea.videoRental.payload.request.ActorGenreRequest}
	 * @return ActorGenreResponse<List> {@link it.rea.videoRental.payload.response.ActorGenreResponse}
	 */
	@GetMapping("/actor-genre") 
	public ResponseEntity<?> actoreGenre(@Valid @RequestBody ActorGenreRequest request){
		// check actors by surname pass in request. If not interrupt method execution  httpStatus 404
		Optional<List<Staff>> s = staffService.getStaffByLastname(request.getLastname().toUpperCase().trim());
		if( !s.isPresent() )
			return new ResponseEntity<String>("No staff found for the given lastname", HttpStatus.NOT_FOUND);
		// check genre by genrename pass in request. If not interrupt method execution  httpStatus 404
		Optional<Genre> g = genreService.getGenreByName(request.getGenrename().trim());
		if ( !g.isPresent() )
			return new ResponseEntity<String>("No genre found for the given name", HttpStatus.NOT_FOUND);
		//create staff ids list by the given surname
		Optional<List<Long>> staffIds = staffService.staffIdByLastname(request.getLastname());
		// generate ActoreGenreResponse<List>
		List<ActorGenreResponse> list = staffService.getFilmsByActorsAndGenre(staffIds.get(), g.get().getGenreName());
		// return response entity and httpStatus 200
		return new ResponseEntity<List<ActorGenreResponse>>(list, HttpStatus.OK);
	}
	


	
}
