package com.digitalways.chillandfish.messages;

public class AuthToken {
    private String securityKey;
    private Boolean authorized;
    private String sessionId;

    public AuthToken(String securityKey, Boolean authorized, String sessionId) {
        this.securityKey = securityKey;
        this.authorized = authorized;
        this.sessionId = sessionId;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public String getSessionId() {
        return sessionId;
    }
}
