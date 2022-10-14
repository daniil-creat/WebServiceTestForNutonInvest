package com.example.webservice;

import com.example.webservice.dto.ErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleException(RuntimeException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
