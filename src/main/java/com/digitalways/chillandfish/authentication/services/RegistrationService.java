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
import com.digitalways.chillandfish.persistence.FinancialData;
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

        if(roleRepo.findRoleByRoleName(message.getGroupName()).isPresent()) {
            System.out.println("Processing registration info....");
            Role role = roleRepo.findRoleByRoleName(message.getGroupName()).get();

            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);
            FinancialData financialData = new FinancialData(message.getCreateUserId(),message.getFinancialInfo().getBankAccountNr());
            User savedUser = userRepo.save(
                    new User(
                            message.getFirstName(), message.getLastName(),
                            message.getLoginname(),message.getPassword(),
                            roles,
                            message.getDisplayName(),
                            message.getAddress(),
                            message.getContactData(),
                            financialData,
                            message.getCreateUserId() )
            );
            System.out.println("User registered successfully!");

            return new RegistrationResponse(savedUser.getId(), savedUser.getDisplayName(), savedUser.getRoles(),
                    savedUser.getAddress(), savedUser.getFinancialData(), savedUser.getContactInfo());
        }
        else {
            System.out.println("Unsuccessful user registration!");
            return null; } //TODO throw a business exception
    }
}
