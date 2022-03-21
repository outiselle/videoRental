package it.rea.videoRental.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.rea.videoRental.entity.Inventory;
import it.rea.videoRental.repository.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired InventoryRepository inventoryRepository;	
	
	/**
	 * <p>Add film Entity into store one</p>
	 * @param i Inventory Entity
	 */
	public void addFilmToStore(Inventory i) {
		inventoryRepository.save(i);
	}

	/**
	 * <p>Method related to findByInventory query {@link it.rea.videoRental.repository.InventoryRepository.findByInventoryId}</p>
	 * @param id long
	 * @return optional Inventory Entity
	 */
	public Optional<Inventory> getInventoryById(long id){
		return inventoryRepository.findByInventoryId(id);
	}
	
}
