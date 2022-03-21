package it.rea.videoRental.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @AllArgsConstructor @NoArgsConstructor @Setter
public class ActorGenreRequest {
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	private String genrename;

}
