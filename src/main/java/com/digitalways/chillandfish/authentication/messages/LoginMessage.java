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
public class LoginMessage extends BaseMessage{
    @JsonProperty("loginName")
    private String loginname;
    
    @JsonProperty("password")
    private String Password;
    
    @JsonProperty("groupName")
    private String groupName; 
    
    @JsonProperty("loginPersistent")
    private boolean loginPersistent;

    public String getLoginname() {
        return loginname;
    }

    public String getPassword() {
        return Password;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean isLoginPersistent() {
        return loginPersistent;
    }    
    
}
