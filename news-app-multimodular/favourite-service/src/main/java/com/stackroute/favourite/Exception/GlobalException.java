package com.stackroute.favourite.Exception;

import org.apache.catalina.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = CountException.class)
    public String CountExceedsException(CountException exception){
        String e = exception.getMessage();
        return "Count exceeds ";
    }


}
