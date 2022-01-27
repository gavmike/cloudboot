package ru.javaops.bootjava;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SingException.class)
    public ResponseEntity<String> hadlerException(SingException e){
        System.out.println("hadle exc sing");
        return new ResponseEntity<String>("hand", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        System.out.println("hadle exc general");
        return new ResponseEntity<>(new Error()
               , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
