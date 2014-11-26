package jga;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User implements Serializable {
	public User(String firstName, String lastName, String email,
			String username, String password, Date dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	@JoinTable(
			name="SPONSOR_SPONSORED",
			joinColumns={@JoinColumn(name="SPONSOR_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="SPONSORED_ID", referencedColumnName="ID")})
	private List<User> sponsored;
	
	@ManyToMany(mappedBy="sponsored")
	private List<User> sponsors;

	public User() {
		super();
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}   
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}   
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}   
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<User> getSponsored() {
		return sponsored;
	}
	public void setSponsored(List<User> sponsored) {
		this.sponsored = sponsored;
	}
	public List<User> getSponsors() {
		return sponsors;
	}
	public void setSponsors(List<User> sponsors) {
		this.sponsors = sponsors;
	}
}
