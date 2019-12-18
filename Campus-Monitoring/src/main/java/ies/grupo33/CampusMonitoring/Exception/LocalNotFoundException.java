package ies.grupo33.CampusMonitoring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LocalNotFoundException extends Exception{
	
	public LocalNotFoundException(String s) {
        super(s);
    }

}
