package com.digitalways.chillandfish.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class DummyMessage implements Serializable {
    @JsonProperty("dummyMsg")
    private String dummyMsg;

    public String getDummyMsg() {
        return dummyMsg;
    }

    public void setDummyMsg(String dummyMsg) {
        this.dummyMsg = dummyMsg;
    }
}
