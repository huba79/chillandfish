package com.digitalways.chillandfish.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DummyResponse {
    @JsonProperty("dummyResponse")
    private String dummyResponse;

    public DummyResponse(String msg) {
        this.dummyResponse = msg;
    }

    public void setDummyResponse(String dummyResponse) {
        this.dummyResponse = dummyResponse;
    }
}
