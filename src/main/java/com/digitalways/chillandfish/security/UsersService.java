package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.messages.UserResponse;
import com.digitalways.chillandfish.persistence.Role;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.repositories.UserRepository;
import com.digitalways.chillandfish.security.exceptions.LoginUnsuccesfulException;
import com.digitalways.chillandfish.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author huba
 */
@Service
public class UsersService implements Converter<Role>, UserDetailsService {
    @Autowired
    UserRepository userRepo;

    public UserResponse getUserById(Long userId) throws LoginUnsuccesfulException {
        try {
            Optional<User> savedUser = userRepo.findById(userId);
            if (savedUser.isPresent()) {
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserNotFoundException("No users found! incorrect id?");
        }
        return null;
    }


    @Override
    public List<Role> setToList(Set<Role> set) {
        List<Role> roles = new ArrayList<>();
        roles.addAll(set);
        return roles;
    }

    @Override //TODO: check differences from said Entity and UserDetails
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findActiveUserByLoginname(username).orElseThrow(() -> new NoSuchElementException("Bad credentials!"));
    }

}
