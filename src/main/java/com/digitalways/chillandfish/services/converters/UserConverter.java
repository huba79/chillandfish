package com.digitalways.chillandfish.services.converters;

import com.digitalways.chillandfish.messages.RegistrationMessage;
import com.digitalways.chillandfish.messages.RegistrationResponse;
import com.digitalways.chillandfish.messages.UserMessage;
import com.digitalways.chillandfish.messages.UserResponse;
import com.digitalways.chillandfish.persistence.User;

public class UserConverter {
    private User user;
    private RegistrationResponse regResponse;
    private UserResponse userResponse;

    public RegistrationResponse toRegResponse(User pUser) {
        return null;
    }

    public UserResponse toUserResponse(User pUser) {
        return null;
    }

    public User regMessageToUser(RegistrationMessage pRegMessage) {
        return null;
    }

    public User userMessageToUser(UserMessage pUserMessage) {
        return null;
    }

}
