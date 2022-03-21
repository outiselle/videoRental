package it.rea.videoRental.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import it.rea.videoRental.entity.composite_key.FilmStaffId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FilmStaff {
	
	@EmbeddedId
	private FilmStaffId filmStaffId;

	@Override
	public int hashCode() {
		return Objects.hash(filmStaffId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmStaff other = (FilmStaff) obj;
		return Objects.equals(filmStaffId, other.filmStaffId);
	}
	
	

}
