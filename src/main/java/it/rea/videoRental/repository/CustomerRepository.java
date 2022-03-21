package it.rea.videoRental.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	/**
	 * <p>Jpa keyword query looking for Customer Entity for the given id
	 * </p>
	 * @param id long
	 * @return optional Customer Entity
	 */
	Optional<Customer> findByCustomerId (long id);

}
