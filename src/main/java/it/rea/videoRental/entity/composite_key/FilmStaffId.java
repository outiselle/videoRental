package it.rea.videoRental.entity.composite_key;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.rea.videoRental.entity.Film;
import it.rea.videoRental.entity.Role;
import it.rea.videoRental.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FilmStaffId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film filmId;
	
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staffId;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roleId;

	@Override
	public int hashCode() {
		return Objects.hash(filmId, roleId, staffId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmStaffId other = (FilmStaffId) obj;
		return Objects.equals(filmId, other.filmId) && Objects.equals(roleId, other.roleId)
				&& Objects.equals(staffId, other.staffId);
	}
	


}
