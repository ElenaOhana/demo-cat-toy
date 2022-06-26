package com.example.web.democattoy.advice;

import com.example.web.democattoy.exceptions.DemoCatToyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class CatToyExceptionHandler {

    @ExceptionHandler(DemoCatToyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleDemoCatToyException(DemoCatToyException exception) {
        return new ErrDetails(exception.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleIOException(Exception e){
        return new ErrDetails(e.getMessage());
    }

    //TODO later-add validate dependency
   /* @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }*/
}
