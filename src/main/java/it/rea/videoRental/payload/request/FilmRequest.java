package it.rea.videoRental.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter 
public class FilmRequest {
	
	@NotBlank @Size(min = 2, max = 100)
	private String title;

	@NotBlank @Size(min = 10, max = 65535)
	private String description;
	
	@NotNull
	private short releaseYear;
	
	@NotNull
	private long languageId;
	
	@NotNull
	private long genreId;

}
