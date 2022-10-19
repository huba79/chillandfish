package com.digitalways.chillandfish.security.exceptions;

/**
 * @author huba
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
