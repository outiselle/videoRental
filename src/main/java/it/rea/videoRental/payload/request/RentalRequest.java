package it.rea.videoRental.payload.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RentalRequest {
	
	@NotNull
	private long customerId;
	
	@NotNull
	private long inventoryId;
	
}
