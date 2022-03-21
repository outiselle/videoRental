package it.rea.videoRental.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.rea.videoRental.entity.Inventory;
import it.rea.videoRental.service.InventoryService;

@RestController
public class InventoryController {
	
	@Autowired InventoryService inventoryService;
	
	/**
	 * <p>Get Inventory Entity by the given id</p>
	 * @param id long
	 * @return optional Inventory Entity
	 */
	public Optional<Inventory> getInventoryById(long id){
		return inventoryService.getInventoryById(id);
	}

}
