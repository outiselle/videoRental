package it.rea.videoRental.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public class FilmRentResponse {
	
	private long filmId;
	
	private String title;
	
	private String storeName;
	

}
