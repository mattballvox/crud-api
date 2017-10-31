package com.crud.api.validator.exception;

/**
 * An Exception that will be thrown if the incoming request doesn't pass some simple validation.
 * 
 * @author mball
 */
public class UserValidationException extends Exception {

    public UserValidationException(String message) {
        super(message);
    }
}
