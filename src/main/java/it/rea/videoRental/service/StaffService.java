package it.rea.videoRental.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Staff;
import it.rea.videoRental.payload.response.ActorGenreResponse;
import it.rea.videoRental.repository.FilmStaffRepository;
import it.rea.videoRental.repository.StaffRepository;

@Service
public class StaffService {
	
	@Autowired FilmStaffRepository filmStaffRepository;
	@Autowired StaffRepository staffRepository;
	
	/**
	 * <p>Method related to jpa keyword find by property lastname in Staff Entity
	 * @param lastname for actor. Type String
	 * @return optional staff List
	 */
	public Optional<List<Staff>> getStaffByLastname(String lastname){
		return staffRepository.findByLastname(lastname);
	}
	
	/**
	 * <p>Method related to getFilmsByActor query {@link it.rea.videoRental.repository.FilmStaffRepository#getFilmsByActorsAndGenre(Collection, String)}
	 * </p>
	 * @param staffIds list of staff ids. Type long
	 * @param genre name. Type String
	 * @return ActorGenreResponse list of object
	 */
	public List<ActorGenreResponse> getFilmsByActorsAndGenre(Collection<Long> staffIds, String genre){
		return filmStaffRepository.getFilmsByActorsAndGenre(staffIds, genre);
	}
		
	/**
	 * <p>Method related to staffIdByLastName {@link it.rea.videoRental.repository.StaffRepository#staffIdByLastname(String)}</p>
	 * @param lastname String
	 * @return optional ids list type long
	 */
	public Optional<List<Long>> staffIdByLastname(String lastname){
		return staffRepository.staffIdByLastname(lastname);
	}
	
}
