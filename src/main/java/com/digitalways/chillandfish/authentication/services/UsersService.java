/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digitalways.chillandfish.authentication.services;

import com.digitalways.chillandfish.authentication.messages.UserResponse;
import com.digitalways.chillandfish.authentication.persistence.Role;
import com.digitalways.chillandfish.authentication.persistence.User;
import com.digitalways.chillandfish.authentication.repositories.UserRepository;
import com.digitalways.chillandfish.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author huba
 */
@Service
public class UsersService implements Converter<Role> {
    @Autowired UserRepository userRepo;
    public UserResponse getUserById(Long userId) throws UnsuccesfulLoginException {
        try{
            Optional<User> savedUser = userRepo.findById(userId);
            if(savedUser.isPresent()){
                return new UserResponse(savedUser.get().getId(),
                        savedUser.get().getFirstName(),
                        savedUser.get().getLastName(),
                        savedUser.get().getDisplayName(),
                        savedUser.get().getLoginName(),
                        savedUser.get().getActive(),
                        savedUser.get().getAddress(),
                        savedUser.get().getContactInfo(),
                        setToList(savedUser.get().getRoles()),
                        savedUser.get().getFinancialData()
                );
            }
            } catch(Exception e){
                e.printStackTrace();
                throw new UserNotFoundException("No users found! incorrect id?");
            }
        return null;
        }


    @Override
    public List<Role> setToList(Set<Role> set) {
        List<Role> roles = new ArrayList<>();
        for(Role role:set){
            roles.add(role);
        }
        return roles;
    }
}
