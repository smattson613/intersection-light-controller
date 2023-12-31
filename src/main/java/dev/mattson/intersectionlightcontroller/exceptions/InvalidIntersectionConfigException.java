package dev.mattson.intersectionlightcontroller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidIntersectionConfigException extends RuntimeException{

    public InvalidIntersectionConfigException(String message) {
        super(message);
    }
}
