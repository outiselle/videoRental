package it.rea.videoRental.entity;

import java.util.Objects;

import javax.persistence.Column;
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
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long filmId;
	
	@Column(length = 100, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable =  false)
	private String description;
	
	@Column(nullable = false)
	private short releaseYear;
	
	@ManyToOne
	@JoinColumn(name = "language_id", nullable = false)
	private Language languageId;
	
	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genreId;

	public Film(String title, String description, short releaseYear, Language languageId, Genre genreId) {
		super();
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.genreId = genreId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return filmId == other.filmId;
	}

	

	
	
	

	
	

}
