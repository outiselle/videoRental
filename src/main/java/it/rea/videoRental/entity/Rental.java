package it.rea.videoRental.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import it.rea.videoRental.entity.composite_key.RentalId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor 
public class Rental {

	@EmbeddedId
	private RentalId rentalId;
	
	private LocalDateTime rentalReturn;

	@Override
	public int hashCode() {
		return Objects.hash(rentalId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		return Objects.equals(rentalId, other.rentalId);
	}

	public Rental(RentalId rentalId, LocalDateTime rentalReturn) {
		super();
		this.rentalId = rentalId;
		this.rentalReturn = null;
	}

	
	
	
	
	
}
