package it.rea.videoRental.entity.composite_key;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import it.rea.videoRental.entity.Customer;
import it.rea.videoRental.entity.Inventory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor 
public class RentalId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;
	
	@ManyToOne
	@JoinColumn(name = "inventory_id")
	private Inventory inventoryId;
	
	// this field is set to current data when entity is created
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime rentalDate;

	@Override
	public int hashCode() {
		return Objects.hash(customerId, inventoryId, rentalDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentalId other = (RentalId) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(inventoryId, other.inventoryId)
				&& Objects.equals(rentalDate, other.rentalDate);
	}

	public RentalId(Customer customerId, Inventory inventoryId, LocalDateTime rentalDate) {
		super();
		this.customerId = customerId;
		this.inventoryId = inventoryId;
		this.rentalDate = LocalDateTime.now();
	}
	
	
	
	
	
	

}
