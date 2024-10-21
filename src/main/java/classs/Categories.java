package classs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = "Categories.findById", query = "SELECT o FROM Categories o WHERE o.id = :id"),
		@NamedQuery(name = "Categories.findAll", query = "FROM Categories")})
@Entity
@Table(name = "CATEGORIES")
public class Categories {

	private String id;
	private String name;

	public Categories() {
	}

	public Categories(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
