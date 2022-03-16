package com.epam.hotelbooking.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = -2568147133557051912L;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
