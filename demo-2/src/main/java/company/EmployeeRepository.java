package company;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{
	Collection<Employee> findByTeamTeamName(String name);

}
