package com.example.Product_Managment_System.Exception;

import com.example.Product_Managment_System.DTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerExeption(Exception e)
    {
      ErrorResponse error=  ErrorResponse.builder().status(HttpStatus.INTERNAL_SERVER_ERROR
              .value()).message("We are doing operation with null values").build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParameterException(Exception e)
    {
        ErrorResponse error=  ErrorResponse.builder().status(HttpStatus.BAD_REQUEST
                .value()).message(e.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
