/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.messages.AuthToken;
import com.digitalways.chillandfish.messages.LoginMessage;
import com.digitalways.chillandfish.messages.LoginResponse;
import com.digitalways.chillandfish.persistence.Role;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author huba
 */
@Service
public class AuthenticationService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    AuthorizationService authorizationService;

    public LoginResponse login(LoginMessage loginMessage) throws UnsuccesfulLoginException {
        try {
            Optional<User> foundUser = userRepo.findUserByLoginNameAndPassword(loginMessage.getLoginname(), loginMessage.getPassword());

            if (foundUser.isPresent()) {
                System.out.println("user found...");
                User user = foundUser.get();
                if (!user.getActive()) {
                    throw new UnsuccesfulLoginException("Unsuccesfull login. User is inactive");
                } else {
                    System.out.println("user is active...");
                    if (authorizationService.confirmProvidedRole(user, loginMessage.getGroupName())) {
                        System.out.println("user role is ok...");
                        Role foundRole = user.getRoleByRoleName(loginMessage.getGroupName());
                        AuthToken token = authorizationService.authorize(user, foundRole);
                        return new LoginResponse(user.getId(), user.getDisplayName(), token.getSessionId(), token.getSecurityKey());
                    } else throw new UnsuccesfulLoginException("Unsuccesfull login. Invalid or empty role.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsuccesfulLoginException(e.getMessage());
        }
        return null;
    }


}
