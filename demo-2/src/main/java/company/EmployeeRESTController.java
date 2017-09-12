package company;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeRESTController {
	private final EmployeeRepository employeeRepository;
	private final TeamRepository teamRepository;
	private Iterable<Employee> employees;

	@Autowired
	public EmployeeRESTController(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
		this.employeeRepository = employeeRepository;
		this.teamRepository = teamRepository;
	}

//	@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	Iterable<Employee> readEmployees() {
//		return this.employeeRepository.findAll();
//	}

	@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Iterable<Employee>> getAllEmployeesJSON() {
		employees =  employeeRepository.findAll();
		return new ResponseEntity<Iterable<Employee>>(employees, HttpStatus.OK);
	}

	/**
	 * HTTP POST - Create new Employee
	 */
	@RequestMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		Team t = teamRepository.save(new Team("SPR"));
		employeeRepository.save(new Employee(t, employee.firstName, employee.lastName, employee.email, employee.job));
		return new ResponseEntity<String>(HttpStatus.CREATED);

	}
	
	 /**
     * HTTP PUT - Update employee
     * */
    @RequestMapping(value = "/employees/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee)
    {
        Employee emp = employeeRepository.findOne((long) id);
        if(emp != null){
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setEmail(employee.getEmail());
            emp.setJob(employee.getJob());
            employeeRepository.save(employee);
            return new ResponseEntity<Employee>(emp, HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }
     
    /**
     * HTTP DELETE - Delete employee
     * */
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id)
    {
        Employee employee = employeeRepository.findOne((long) id);
        if(employee != null){
        	employeeRepository.delete(employee);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

}