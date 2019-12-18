package ies.grupo33.CampusMonitoring.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WeatherReadingNotFoundException extends Exception{

	public WeatherReadingNotFoundException(String s) {
        super(s);
    }

}
