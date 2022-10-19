/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.messages.RegistrationMessage;
import com.digitalways.chillandfish.messages.RegistrationResponse;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.digitalways.chillandfish.persistence.Role;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.repositories.RoleRepository;
import com.digitalways.chillandfish.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author huba
 */
@Service
public class RegistrationService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;

    public RegistrationResponse createUser(RegistrationMessage message) throws RegistrationUnsuccessfulException {

        System.out.println("Processing registration info....");

        if (isValidRole(message.getGroupName())) {
            System.out.println("The received role is valid... Checking if user exists");
            if (doesUserExist(message)) {
                System.out.println("Loginname already taken...");
                System.out.println("Unsuccessful user registration! Loginname already taken.");
                throw new RegistrationUnsuccessfulException("Unsuccessful user registration! Loginname already taken.");
            } else {
                //loginname is a new valid loginname
                System.out.println("Loginname is available. Saving user...");

                Role role = roleRepo.findRoleByRoleName(message.getGroupName()).get();
                ContactData contactData = (message.getContactData() == null) ? null : new ContactData(message.getContactData().getPhoneNr(), message.getContactData().getPhoneNr(), message.getCreateUserId());
                FinancialData financialData = (message.getFinancialInfo() == null) ? null : new FinancialData(message.getCreateUserId(), message.getFinancialInfo().getBankAccountNr());
                Set<Role> roles = new LinkedHashSet<>();
                roles.add(roleRepo.findRoleByRoleName(message.getGroupName()).get());
                User user = new User(
                        message.getFirstName(), message.getLastName(),
                        message.getLoginname(), message.getPassword(),
                        roles, //TODO: remember to set it
                        message.getDisplayName(),
                        message.getAddress(),
                        contactData,
                        financialData,
                        message.getCreateUserId());


                User savedUser = userRepo.save(user);
                System.out.println("User registered successfully!");

                return new RegistrationResponse(savedUser.getId(), savedUser.getDisplayName(), savedUser.getRoles(),
                        savedUser.getAddress(), savedUser.getFinancialData(), savedUser.getContactInfo());
            }

        } else {
            System.out.println("Unsuccessful user registration! Invalid user group provided.");
            throw new RegistrationUnsuccessfulException("Unsuccessful user registration! Invalid user group provided.");
        } //TODO throw a business exception
    }

    private boolean isValidRole(String roleName) {
        return roleRepo.findRoleByRoleName(roleName).isPresent();
    }

    private boolean doesUserExist(RegistrationMessage message) {
        return userRepo.findActiveUserByLoginname(message.getLoginname()).isPresent();
    }
}
