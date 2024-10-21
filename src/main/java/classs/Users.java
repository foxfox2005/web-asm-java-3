package classs;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@NamedQueries({ @NamedQuery(name = "Users.findById", query = "SELECT o FROM Users o WHERE o.id = :id"),
		@NamedQuery(name = "Users.findAll", query = "FROM Users") })
@Entity
@Table(name = "USERS")
public class Users {

	private String id;
	private String password;
	private String fullname;
	private Date birthday;
	private Boolean gender;
	private String mobile;
	private String email;
	private boolean role;

	public Users() {
	}

	public Users(String id, String password, String fullname, String email, boolean role) {
		this.id = id;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.role = role;
	}

	public Users(String id, String password, String fullname, Date birthday, Boolean gender, String mobile,
			String email, boolean role) {
		this.id = id;
		this.password = password;
		this.fullname = fullname;
		this.birthday = birthday;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.role = role;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "fullname", nullable = false)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", length = 10)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "gender")
	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	@Column(name = "mobile", length = 10)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", nullable = false, length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "role", nullable = false)
	public boolean isRole() {
		return this.role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}
}
