package com.digitalways.chillandfish.messages;

import java.io.Serial;
import java.io.Serializable;

public class AuthenticationJwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public AuthenticationJwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}