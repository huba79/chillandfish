/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author huba
 */
public class LoginResponse extends BaseMessage{
    @JsonProperty("userId")
    private Long userId;
    
    @JsonProperty("displayName")
    private String displayName;
    
    @JsonProperty("sessionId")
    private String sessionId;
    
    @JsonProperty("token")
    private String securityToken;

    public LoginResponse(Long userId, String displayName, String sessionId, String securityToken) {
        this.userId = userId;
        this.displayName = displayName;
        this.sessionId = sessionId;
        this.securityToken = securityToken;
    }    

    public LoginResponse() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    
    
    
}
