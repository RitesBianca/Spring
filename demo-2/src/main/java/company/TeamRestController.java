package company;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/{teamName}/company")
public class TeamRestController {
	private final TeamRepository teamRepository;
	private final EmployeeRepository employeeRepository;

	@Autowired
	public TeamRestController(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
		this.teamRepository = teamRepository;
		this.employeeRepository = employeeRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Employee> readEmployees(@PathVariable String teamName) {
		this.validateTeam(teamName);
		return this.employeeRepository.findByTeamTeamName(teamName);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{employeeId}")
	Employee readEmployee(@PathVariable String teamName, @PathVariable Long employeeId) {
		this.validateTeam(teamName);
		return this.employeeRepository.findOne(employeeId);
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable String teamName, @RequestBody Employee input) {
		this.validateTeam(teamName);

		return this.teamRepository.findByTeamName(teamName).map(team -> {
			Employee result = employeeRepository
					.save(new Employee(team, input.firstName, input.lastName, input.email, input.job));

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
					.toUri();

			return ResponseEntity.created(location).build();
		}).orElse(ResponseEntity.noContent().build());

	}

	private void validateTeam(String teamName) {
		this.teamRepository.findByTeamName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
	}
}
