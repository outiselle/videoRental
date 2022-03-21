package it.rea.videoRental.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.rea.videoRental.entity.Customer;
import it.rea.videoRental.entity.Inventory;
import it.rea.videoRental.entity.Rental;
import it.rea.videoRental.entity.composite_key.RentalId;
import it.rea.videoRental.payload.request.RentalRequest;
import it.rea.videoRental.service.CustomerService;
import it.rea.videoRental.service.RentalService;

@RestController
public class RentalController {
	
	@Autowired RentalService rentalService;
	@Autowired InventoryController inventoryController;
	@Autowired CustomerService customerService;
	
	// Controller 09
	/**
	 * <p>This method allow add or update rentals for customers
	 * </p>
	 * @param rentReq {@link it.rea.videoRental.payload.request.RentalRequest}
	 * @return response entity when add or update rental with httpStatus 200 and httpStatus 400 for errors
	 */
	@PostMapping("/add-update-rental")
	public ResponseEntity<?> addUpdateRental (@RequestBody RentalRequest rentReq){
		// check customer for the given id into rentReq.  If not interrupt method execution  httpStatus 404
		Optional<Customer> c = customerService.getCustomerById(rentReq.getCustomerId());
		if( !c.isPresent() )
			return new ResponseEntity<String>("Customer not found for the given id, please check", HttpStatus.NOT_FOUND);
		// check inventory for the given id into rentReq.  If not interrupt method execution  httpStatus 404
		Optional<Inventory> i = inventoryController.getInventoryById(rentReq.getInventoryId());
		if( !i.isPresent() )
			return new ResponseEntity<String>("Inventory not found for the given id", HttpStatus.NOT_FOUND);
		// check if film is rent by the given customer. If it is update rental is available
		Optional<Rental> rentOut = rentalService.getRentOutByCustAndInv(c.get().getCustomerId(), rentReq.getInventoryId());
		if(rentOut.isPresent()) {
			rentOut.get().setRentalReturn(LocalDateTime.now());
			rentalService.saveRental(rentOut.get());
			return new ResponseEntity<String>("Customer " + c.get().getFirstname() + " " + c.get().getLastname() + 
					" returned inventory " + rentReq.getInventoryId(), 
					HttpStatus.OK);
		} else {
			//if film isn't rent by customer check if is available by inventory where store in. If it is rental is available
			Optional<List<Rental>> rentsByInv = rentalService.getRentalsOutByInvId(i.get().getInventoryId());
			if(rentsByInv.get().isEmpty()) {
				Rental r = new Rental();
				r.setRentalId(new RentalId(c.get(), i.get(), LocalDateTime.now()));
				rentalService.saveRental(r);
				return new ResponseEntity<String>("Customer " + c.get().getFirstname() + " " + c.get().getLastname() +
						" rents film from Inventory " + rentReq.getInventoryId(), 
						HttpStatus.OK);
			} else {
				// if movie is already rent msg says to check availability from another inventory
				return new ResponseEntity<String>("Movie not available for the given inventory. Please check another one", 
						HttpStatus.NOT_FOUND);
			}
		}
					
	}
	
	
			
			
		
		
		
			
		
	
		
}
	



