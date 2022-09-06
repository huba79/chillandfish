/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digitalways.chillandfish.authentication.services;

import com.digitalways.chillandfish.authentication.messages.LoginMessage;
import com.digitalways.chillandfish.authentication.messages.LoginResponse;
import com.digitalways.chillandfish.authentication.repositories.RoleRepository;
import com.digitalways.chillandfish.authentication.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author huba
 */
@Service
public class AuthenticationService {
    @Autowired UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    
    LoginResponse authenticate(LoginMessage loginMessage) throws UnsuccesfulLoginException {
        try{
            Optional foundUser = userRepo.findActiveUserByDisplayNameAndPassword(loginMessage.getLoginname(), loginMessage.getPassword());
            if (foundUser.isEmpty() ) {
                throw new UnsuccesfulLoginException("Unsuccessful login! Did you provide the right credentials?");
            } else {
              String groupName = roleRepo.findRoleByRoleName(loginMessage.getGroupName()).get().getRoleName();
              if (!groupName.equals(loginMessage.getGroupName())) {
                  throw new RegistrationUnsuccessfulException("Invalid user group provided!");
              } else {

//              TODO: generate sessionID    
//              TODO: generate security token
//              return new LoginResponse();
              }
            }
        
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
