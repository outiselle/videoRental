package it.rea.videoRental.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Rental;
import it.rea.videoRental.entity.composite_key.RentalId;
import it.rea.videoRental.payload.response.FilmRentResponse;

@Repository
public interface RentalRepository extends JpaRepository<Rental, RentalId> {
	
	/**
	 * <p>This query count customer id from the given store name</p>
	 * @param store String
	 * @return int customer count
	 */
	@Query(value = "SELECT COUNT(DISTINCT r.rentalId.customerId)"
			+ "FROM Rental r "
			+ "WHERE r.rentalId.inventoryId.storeId.storeName= :store")
	int countCustomerByStore(@Param("store") String store);
	
	/**
	 * <p>This query count rentals for the given store in a specific range</p>
	 * @param id long
	 * @param start LocalDateTime
	 * @param end LocalDateTime
	 * @return int rentals count
	 */
	@Query(value = "SELECT COUNT(r) "
			+ "FROM Rental r "
			+ "WHERE r.rentalId.rentalDate BETWEEN :start AND :end "
			+ "AND r.rentalId.inventoryId.storeId.storeId = :id")
	int countRentalsInRange (@Param("id") long id, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	/**
	 * <p>This query get film_id, title and associated store name for each rental by the given Customer pass through id
	 * </p>
	 * @param id long
	 * @return FilmRentResponse list {@link it.rea.videoRental.payload.response.FilmRentResponse}
	 */
	@Query(value = "SELECT new it.cgmconsulting.examDroetti.payload.response.FilmRentResponse("
			+ "r.rentalId.inventoryId.filmId.filmId, "
			+ "r.rentalId.inventoryId.filmId.title, "
			+ "r.rentalId.inventoryId.storeId.storeName"
			+ ") "
			+ "FROM Rental r "
			+ "WHERE r.rentalId.customerId.customerId = :id")
	List<FilmRentResponse> filmsRentByCustomerId (@Param("id") long id);
		
	/**
	 * <p>This query get Rental info by the given customer and inventory id
	 * @param custId long
	 * @param invId long
	 * @return optional Rental Entity
	 */
	@Query(value = "SELECT r FROM Rental r "
			+ "WHERE r.rentalId.customerId.customerId = :customer "
			+ "AND r.rentalId.inventoryId.inventoryId = :inventory "
			+ "AND r.rentalReturn IS NULL")
	Optional<Rental> getRentOutByCustomerAndInventory(@Param("customer") Long custId, @Param("inventory") Long invId);
	
	/**
	 * <p>This query get Rental Entity List for the given inventory id</p>
	 * @param invId long
	 * @return optional list Rental 
	 */
	@Query(value = "SELECT r FROM Rental r "
			+ "WHERE r.rentalId.inventoryId.inventoryId = :invId "
			+ "AND r.rentalReturn IS NULL")
	Optional<List<Rental>> getRentalsOutByInventoryId (@Param("invId") long invId);
	
}
