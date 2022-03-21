package it.rea.videoRental.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Store;
import it.rea.videoRental.repository.RentalRepository;
import it.rea.videoRental.repository.StoreRepository;

@Service
public class StoreService {
	
	@Autowired StoreRepository storeRepository;
	@Autowired RentalRepository rentalRepository;
	
	/**
	 * <p>Service related to findByStoreId query {@link it.rea.videoRental.repository.StoreRepository#findByStoreId(long)}
	 * </p>
	 * @param id long
	 * @return store entity
	 */
	public Optional<Store> getStoreById (long id){
		return storeRepository.findByStoreId(id);
	}
	
	/**
	 * <p>Method related to findByStoreName keyword {@link it.rea.videoRental.repository.StoreRepository#findByStoreName(String)}
	 * </p>
	 * @param storeName String
	 * @return optional Store Entity
	 */
	public Optional<Store> getStoreByname (String storeName){
		return storeRepository.findByStoreName(storeName);
	}
	
	/**
	 * <p>Method related to countCustomerByStore query {@link it.rea.videoRental.repository.RentalRepository#countCustomerByStore(String)}
	 * </p>
	 * @param store String
	 * @return customer count by store
	 */
	public int getCustomerByStore (String store) {
		return rentalRepository.countCustomerByStore(store);
	}
	
	/**
	 * <p>Method related to countRentalsInRange query {@link it.rea.videoRental.repository.RentalRepository#countRentalsInRange(long, LocalDateTime, LocalDateTime)}
	 * </p>
	 * @param id long
	 * @param start LocalDateTime
	 * @param end LocalDateTime
	 * @return int rentals count
	 */
	public int coutRentalInRange (long id, LocalDateTime start, LocalDateTime end) {
		return rentalRepository.countRentalsInRange(id, start, end);
	}
	
}
