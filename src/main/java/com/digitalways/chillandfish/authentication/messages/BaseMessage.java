/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.messages;

/**
 *
 * @author huba
 */
public abstract class BaseMessage {
    
    private String apiVersion = "1.0"; //dummy init, to develop later maybe using Environment

    public BaseMessage() {
    }
   
    public BaseMessage(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiVersion() {
        return apiVersion;
    }
    
}
