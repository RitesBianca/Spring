package company;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(TeamRepository teamRepository,
			EmployeeRepository employeeRepository) {
		Team team1 = teamRepository.save(new Team("SPR1"));
		Team team2 = teamRepository.save(new Team("PC1"));
		employeeRepository.save(new Employee(team1,"pop","ion","pop.ion@gmail.com","tester"));
		employeeRepository.save(new Employee(team2, "popa","ioana","ioana.popa@gmail.com","programmer"));
		return (evt) -> Arrays.asList(
				"RPD,Prep,PC".split(","))
				.forEach(
						a -> {
							Team team = teamRepository.save(new Team(a));
							employeeRepository.save(new Employee(team,
									"popescu","ion","ion.popescu@gmail.com","programmer"));
							employeeRepository.save(new Employee(team,
									"ionescu","ion","ion.ionescu@gmail.com", "manager"));
						});
}
}