package com.digitalways.chillandfish.security.exceptions;
/**
 * @author huba
 */
public class LoginUnsuccesfulException extends RuntimeException {
    public LoginUnsuccesfulException(String errorMessage) {
        super(errorMessage);
    }
}
