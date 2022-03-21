package it.rea.videoRental.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Store {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long storeId;
	
	@Column(nullable = false, length = 60, unique = true)	
	private String storeName;

	@Override
	public int hashCode() {
		return Objects.hash(storeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return storeId == other.storeId;
	}
	
	

}
