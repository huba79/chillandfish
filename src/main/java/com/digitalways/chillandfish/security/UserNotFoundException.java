package com.digitalways.chillandfish.security;

/**
 * @author huba
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
