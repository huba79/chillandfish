/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.services;

/**
 *
 * @author huba
 */
public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException() {
        super("Inexistent user group!");
    }
    
}
