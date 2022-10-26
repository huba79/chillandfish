package com.digitalways.chillandfish.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AuthenticationMessage implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @JsonProperty("userName")
    private String username;
    @JsonProperty("password")
    private String password;

    public AuthenticationMessage() {}

    public AuthenticationMessage(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}