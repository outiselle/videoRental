package it.rea.videoRental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	
	/**
	 * <p>Find store by the given id through jpa keyword</p>
	 * @param id
	 * @return optional store entity
	 */
	Optional<Store> findByStoreId(long id);
	
	/**
	 * <p>Find store by the given store name through jpa keyword</p>
	 * @param storeName String
	 * @return optional store entity
	 */
	Optional<Store> findByStoreName(String storeName);

}
