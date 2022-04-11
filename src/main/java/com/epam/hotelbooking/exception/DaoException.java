package com.epam.hotelbooking.exception;

public class DaoException extends Exception {

    private static final long serialVersionUID = -4557229183053290212L;

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

}
