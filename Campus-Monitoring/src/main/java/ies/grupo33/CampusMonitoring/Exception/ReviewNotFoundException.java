package ies.grupo33.CampusMonitoring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ReviewNotFoundException(String message){
        super(message);
    }
}
