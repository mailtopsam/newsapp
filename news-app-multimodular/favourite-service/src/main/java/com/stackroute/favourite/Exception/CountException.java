package com.stackroute.favourite.Exception;



public class CountException extends Exception{
    private String message;

    public CountException(String message) {
        super(message);
        this.message = message;
    }

    public CountException() {
    }
}
