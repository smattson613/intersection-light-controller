package dev.mattson.intersectionlightcontroller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class IntersectionExceptionHandler {

    @ExceptionHandler(value = {InvalidIntersectionConfigException.class})
    public ResponseEntity<Object> handleInvalidIntersectionConfigException(InvalidIntersectionConfigException e) {
        IntersectionException intersectionException = new IntersectionException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("America/New_York"))
        );

        return new ResponseEntity<>(intersectionException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IntersectionNotFoundException.class})
    public ResponseEntity<Object> handleIntersectionNotFoundException(IntersectionNotFoundException e) {
        IntersectionException intersectionException = new IntersectionException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("America/New_York"))
        );

        return new ResponseEntity<>(intersectionException, HttpStatus.NOT_FOUND);
    }


}
