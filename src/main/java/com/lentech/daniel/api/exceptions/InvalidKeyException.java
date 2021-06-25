package com.lentech.daniel.api.exceptions;

public class InvalidKeyException extends Exception {

    public InvalidKeyException() {
        super();
    }

    public InvalidKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidKeyException(String message) {
        super(message);
    }

    public InvalidKeyException(Exception e) {
        super(e);
    }
}
