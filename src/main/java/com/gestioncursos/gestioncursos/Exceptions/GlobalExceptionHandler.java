package com.gestioncursos.gestioncursos.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gestioncursos.gestioncursos.Exceptions.Dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> notFoundEx(NoHandlerFoundException e){

        ErrorDto error = new ErrorDto();
        error.setDate(new Date());
        error.setError("Api not found");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDto> handleDuplicateResource(DuplicateResourceException e){
        
        ErrorDto error = new ErrorDto();
        error.setDate(new Date());
        error.setError("Duplicate Resource");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFound(ResourceNotFoundException e){
        
        ErrorDto error = new ErrorDto();
        error.setDate(new Date());
        error.setError("Item not found!");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorDto> invalidInput(InvalidInputException e){

        ErrorDto error = new ErrorDto();
        error.setDate(new Date());
        error.setError("Input invalid");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
