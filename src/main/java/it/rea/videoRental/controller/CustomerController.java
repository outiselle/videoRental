package it.rea.videoRental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.rea.videoRental.entity.Customer;
import it.rea.videoRental.payload.response.FilmRentResponse;
import it.rea.videoRental.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	//controller 11
	/**
	 * <p>Find films rent by customer with the given id
	 * </p>
	 * @param customerId long
	 * @return FilmRentResponse <List>
	 */
	@GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
	public ResponseEntity<?> filmRentByCustomerId (@PathVariable long customerId){
		// check customer for the given id.  If not interrupt method execution  httpStatus 404
		Optional<Customer> c = customerService.getCustomerById(customerId);
		if( !c.isPresent() )
			return new ResponseEntity<String>("Customer not found for the given id", HttpStatus.NOT_FOUND);
		// create FilmRentResponse <List> for the given id
		List<FilmRentResponse> frr = customerService.filmsRentByCustomerId(customerId);
		// return FilmRentResponse and httpStatus 200
		return new ResponseEntity<List<FilmRentResponse>>(frr, HttpStatus.OK);
	}

}
