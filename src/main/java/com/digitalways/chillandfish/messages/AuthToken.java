package com.digitalways.chillandfish.messages;

public class AuthToken {
    private final String securityKey;
    private final Boolean authorized;
    private final String sessionId;

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
