package it.rea.videoRental.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ActorGenreResponse {
	
	private long filmId;
	
	private String filmTitle;
	
	private String firstname;
	
	private String lastname;
	
	private String genre;

}
