package ies.grupo33.CampusMonitoring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class LoginFailedException extends Exception{
	
	
	public LoginFailedException(String message){
        super(message);
    }

}
