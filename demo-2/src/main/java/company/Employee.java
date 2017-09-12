package company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {
	@JsonIgnore
	@ManyToOne
	private Team team;
	@Id
	@GeneratedValue
	private Long id;

	public String firstName;

	public Team getTeam() {
		return team;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getJob() {
		return job;
	}

	public String lastName;
	public void setTeam(Team team) {
		this.team = team;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String email;
	public String job;

	public Employee(Team team, String firstName, String lastName, String email, String job) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.job = job;
		this.team = team;
	}
	Employee(){
		
	}

}
