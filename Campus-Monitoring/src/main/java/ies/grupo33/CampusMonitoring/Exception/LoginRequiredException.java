package ies.grupo33.CampusMonitoring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class LoginRequiredException extends Exception{

	public LoginRequiredException(String message){
        super(message);
    }
	
}
