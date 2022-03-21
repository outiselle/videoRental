package it.rea.videoRental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Rental;
import it.rea.videoRental.repository.RentalRepository;

@Service
public class RentalService {
	
	@Autowired RentalRepository rentalRepository;
	
	/**
	 * <p>Method related to jpa keyword save Rental Entity</p>
	 * @param rental Entity
	 */
	public void saveRental (Rental rental) {
		rentalRepository.save(rental);
	}
	
	/**
	 * <p>Method related to getRentOutByCustomerAndInventory query {@link it.rea.videoRental.repository.RentalRepository#getRentOutByCustomerAndInventory(Long, Long)}</p>
	 * @param customerId long
	 * @param inventoryId long
	 * @return optional Rental
	 */
	public Optional<Rental> getRentOutByCustAndInv(Long customerId, Long inventoryId){
		return rentalRepository.getRentOutByCustomerAndInventory(customerId, inventoryId);
	}
	
	/**
	 * <p>Method related to getRentalsOutByInventoryId query {@link it.rea.videoRental.repository.RentalRepository#getRentalsOutByInventoryId(long)}</p>
	 * @param invId long
	 * @return optional Rental Entity
	 */
	public Optional<List<Rental>> getRentalsOutByInvId (long invId){
		return rentalRepository.getRentalsOutByInventoryId(invId);
	}
	
	
	

}
