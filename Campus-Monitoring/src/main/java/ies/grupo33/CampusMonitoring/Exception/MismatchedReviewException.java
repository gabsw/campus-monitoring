package ies.grupo33.CampusMonitoring.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MismatchedReviewException extends Exception {
    public MismatchedReviewException(String s) {
        super(s);
    }
}
