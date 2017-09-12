package company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Team {

    @OneToMany(mappedBy = "team")
    private Set<Employee> employees = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    @JsonIgnore
    public String teamName;

    public Team(String name) {
        this.teamName = name;
    }

    Team() { // jpa only
    }
}