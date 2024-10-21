package classs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@NamedQueries({ @NamedQuery(name = "Newsletters.findById", query = "SELECT o FROM Newsletters o WHERE o.email = :id"),
		@NamedQuery(name = "Newsletters.findAll", query = "FROM Newsletters") })
@Entity
@Table(name = "NEWSLETTERS")
public class Newsletters {

	private String email;
	private boolean enabled;

	public Newsletters() {
	}

	public Newsletters(String email, boolean enabled) {
		this.email = email;
		this.enabled = enabled;
	}

	@Id
	@Column(name = "Email", unique = true, nullable = false, length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
