package it.rea.videoRental.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long inventoryId;
	
	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store storeId;

	@ManyToOne
	@JoinColumn(name = "film_id", nullable = false)
	private Film filmId;

	@Override
	public int hashCode() {
		return Objects.hash(inventoryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return inventoryId == other.inventoryId;
	}
	
	
	
}
