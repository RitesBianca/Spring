package company;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class TeamNotFoundException extends RuntimeException {

	public TeamNotFoundException(String teamName) {
		super("could not find team '" + teamName + "'.");
	}
}