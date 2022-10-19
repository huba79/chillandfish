/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.security.exceptions;

/**
 * @author huba
 */
public class LoginUnsuccesfulException extends RuntimeException {
    public LoginUnsuccesfulException(String errorMessage) {
        super(errorMessage);
    }
}
