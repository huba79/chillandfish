package com.digitalways.chillandfish.security.exceptions;
/**
 * @author huba
 */
public class LoginUnsuccessfulException extends RuntimeException {
    public LoginUnsuccessfulException(String errorMessage) {
        super(errorMessage);
    }
}
