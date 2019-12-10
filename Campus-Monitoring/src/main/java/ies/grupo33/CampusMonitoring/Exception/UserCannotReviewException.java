package ies.grupo33.CampusMonitoring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UserCannotReviewException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserCannotReviewException(String message){
        super(message);
    }
}
