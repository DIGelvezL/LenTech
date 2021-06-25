package com.lentech.daniel.api.exceptions;

public class PersonException extends Exception {

    public PersonException() {
        super();
    }

    public PersonException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonException(String message) {
        super(message);
    }

    public PersonException(Exception e) {
        super(e);
    }
}
