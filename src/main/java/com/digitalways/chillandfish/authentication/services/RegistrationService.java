/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digitalways.chillandfish.authentication.services;

import com.digitalways.chillandfish.authentication.messages.RegistrationMessage;
import com.digitalways.chillandfish.authentication.messages.RegistrationResponse;
import com.digitalways.chillandfish.authentication.persistence.Role;
import com.digitalways.chillandfish.authentication.persistence.User;
import com.digitalways.chillandfish.authentication.repositories.RoleRepository;
import com.digitalways.chillandfish.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 *
 * @author huba
 */
@Service
public class RegistrationService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;

    public RegistrationResponse createUser(RegistrationMessage message) {

        Role role = (Role) roleRepo.findRoleByRoleName(message.getGroupName()).get();
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        User savedUser = (User) userRepo.save(
                new User(
                message.getLoginname(), message.getPassword(),
                roles,
                message.getFirstName(),  message.getLastName(),
                message.getFirstName()+", "+message.getLastName(),
                message.getCreateUserId() )
        );

        return new RegistrationResponse(savedUser.getId(),  savedUser.getDisplayName(), savedUser.getRoles(),
                savedUser.getAddress(),savedUser.getFinancialData(),savedUser.getContactInfo() );

    }
}
