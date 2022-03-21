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
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	
	@Column(nullable = false, length = 50)	
	private String firstname;

	@Column(nullable = false, length = 50)
	private String lastname;
	
	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return customerId == other.customerId;
	}
	
	
	

}
