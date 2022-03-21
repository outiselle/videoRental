package it.rea.videoRental.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.rea.videoRental.entity.Store;
import it.rea.videoRental.payload.response.CustomerStoreResponse;
import it.rea.videoRental.service.StoreService;

@RestController
public class StoreController {
	
	@Autowired StoreService storeService;
	
	// controller 08
	/**
	 * <p>Get customer count from the given store</p>
	 * @param storeName String
	 * @return CustomerStoreResponse {@link it.rea.videoRental.payload.response.CustomerStoreResponse}
	 */
	@GetMapping("/count-customers-by-store/{storeName}")
	public ResponseEntity<?> customerByStore (@PathVariable String storeName){
		// check sotre by the given store name.If not return httpStatus 404
		Optional<Store> s = storeService.getStoreByname(storeName.toUpperCase().trim());
		if( !s.isPresent() )
			return new ResponseEntity<String>("No store found with the given name", HttpStatus.NOT_FOUND);
		//create response
		CustomerStoreResponse csr = new CustomerStoreResponse();
		csr.setStoreName(storeName);
		//set field totalCustomer with method getCustomerByStore related to jpa query
		csr.setTotalCustomer(storeService.getCustomerByStore(storeName.toUpperCase().trim()));
		//return response entity
		return new ResponseEntity<CustomerStoreResponse>(csr, HttpStatus.OK);
	}
	
	// controller 10
	/**
	 * <p>Get rentals number for the given in a specific range time</p>
	 * @param storeId long
	 * @param start LocalDateTime
	 * @param end LocalDateTime
	 * @return response entity string with store name, rentals count and time range
	 */
	@GetMapping("/count-rentals-in-date-range-by-store/{storeId}")
	public ResponseEntity<?> countRentalInRange (@PathVariable long storeId, 
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime start, 
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime end){
		// check store for the given id. If not return httpStatus 200
		Optional<Store> s = storeService.getStoreById(storeId);
		if( !s.isPresent() )
			return new ResponseEntity<String>("No store found with the given id", HttpStatus.NOT_FOUND);
		// count rentals from counrRentalInRange methos
		int rentNumber = storeService.coutRentalInRange(storeId, start, end);
		// return msg
		return new ResponseEntity<String>("The store " + s.get().getStoreName() + " had " + rentNumber +
				" rentals between " + start + " and " + end,
				HttpStatus.OK);
	}

}


