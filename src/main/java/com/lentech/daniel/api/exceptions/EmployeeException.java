package com.lentech.daniel.api.exceptions;

public class EmployeeException extends Exception {

    public EmployeeException() {
        super();
    }

    public EmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(Exception e) {
        super(e);
    }
}
