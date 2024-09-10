package com.magd.backend_bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                             WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException(InsufficientBalanceException ex,
                                                                WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Additional exception handlers can be added here

    /*
    * Why is WebRequest Used?
Access to request details: You can use it to get information like request headers, parameters, and attributes.
Custom error handling: If you need to include specific details about the request (e.g., the request URL, headers) in your error response, WebRequest allows you to access that information.
Even though you are not using it in your current implementation, it can be helpful if you want to log additional request details or include them in your error response.
*
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    String path = request.getDescription(false); // Gives something like "uri=/api/accounts/2"
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, ex.getMessage() + ". Path: " + path);
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}

    *  */
}
