package it.rea.videoRental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rea.videoRental.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	
	Optional<List<Staff>> findByLastname(String lastname);
	
	/**
	 * <p>This query get staff ids from the given surnames</p>
	 * @param lastname String
	 * @return
	 */
	@Query(value = "SELECT s.staffId "
			+ "FROM Staff s "
			+ "WHERE s.lastname = :lastname")
	Optional<List<Long>> staffIdByLastname(@Param("lastname") String lastname);

}
