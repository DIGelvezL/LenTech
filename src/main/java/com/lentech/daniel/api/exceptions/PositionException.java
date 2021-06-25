package com.lentech.daniel.api.exceptions;

public class PositionException extends Exception {

    public PositionException() {
        super();
    }

    public PositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PositionException(String message) {
        super(message);
    }

    public PositionException(Exception e) {
        super(e);
    }
}
