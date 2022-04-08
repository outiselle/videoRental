package it.rea.videoRental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Inventory;
import it.rea.videoRental.payload.response.FilmStoreResponse;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	
	/**
	 * <p>This query get film_id, title and store_name where to found by the given film id</p>
	 * @param id long
	 * @return FilmStoreResponse list
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.FilmStoreResponse("
			+ "i.filmId.filmId, "
			+ "i.filmId.title, "
			+ "i.storeId.storeName"
			+ ") "
			+ "FROM Inventory i "
			+ "WHERE i.filmId.filmId= :id")
	List<FilmStoreResponse> getFilmStoreResponse(@Param("id") long id);
				
	/**
	 * <p>Jpa keyword find Entity Inventory by his id property</p>
	 * @param id long
	 * @return optional Inventory Entity
	 */
	Optional<Inventory> findByInventoryId(long id);
	
}
