package com.lentech.daniel.api.exceptions;

public class PropertiesException extends Exception {

    public PropertiesException() {
        super();
    }

    public PropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesException(String message) {
        super(message);
    }

    public PropertiesException(Exception e) {
        super(e);
    }
}
