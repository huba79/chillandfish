package com.digitalways.chillandfish.services;

import com.digitalways.chillandfish.messages.RegistrationMessage;
import com.digitalways.chillandfish.messages.RegistrationResponse;
import com.digitalways.chillandfish.messages.UserResponse;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.digitalways.chillandfish.persistence.Role;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.repositories.RoleRepository;
import com.digitalways.chillandfish.repositories.UserRepository;
import com.digitalways.chillandfish.security.exceptions.LoginUnsuccessfulException;
import com.digitalways.chillandfish.security.exceptions.RegistrationUnsuccessfulException;
import com.digitalways.chillandfish.security.exceptions.UserNotFoundException;
import com.digitalways.chillandfish.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author huba
 */
@Service
public class UsersService implements Converter<Role>, UserDetailsService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;

    public RegistrationResponse createUser(RegistrationMessage message) throws RegistrationUnsuccessfulException {

        Logger.getLogger("registrationLogger").log(Level.INFO, "Processing registration info....");

        if (isValidRole(message.getGroupName())) {
            Logger.getLogger("registrationLogger").log(Level.INFO, "The received role is valid... Checking if user exists " + message.getLoginname());
            if (doesUserExist(message)) {
                Logger.getLogger("registrationLogger").log(Level.INFO, "Unsuccessful user registration! Login name already taken.");
                throw new RegistrationUnsuccessfulException("Unsuccessful user registration! Login name already taken.");
            } else {
                //login name is a new valid login name
                Logger.getLogger("registrationLogger").log(Level.INFO, "Login name is available. Saving user..." + message.getLoginname());

                Role role = roleRepo.findRoleByRoleName(message.getGroupName()).get();
                ContactData contactData = (message.getContactData() == null) ? null : new ContactData(message.getContactData().getPhoneNr(), message.getContactData().getPhoneNr(), message.getCreateUserId());
                FinancialData financialData = (message.getFinancialInfo() == null) ? null : new FinancialData(message.getCreateUserId(), message.getFinancialInfo().getBankAccountNr());
                Set<Role> roles = new LinkedHashSet<>();
                roles.add(role);
                //TODO need to learn the more elegant solution
                Map<String, PasswordEncoder> encoders = new HashMap<>();
                encoders.put("bcrypt", new BCryptPasswordEncoder());
                PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);


                User user = new User(
                        message.getFirstName(), message.getLastName(),
                        message.getLoginname(), passwordEncoder.encode(message.getPassword()),
                        roles,
                        message.getDisplayName(),
                        message.getAddress(),
                        contactData,
                        financialData,
                        message.getCreateUserId());

                User savedUser = userRepo.save(user);
                Logger.getLogger("registrationLogger").log(Level.INFO, "User registered successfully!");

                return new RegistrationResponse(savedUser.getId(), savedUser.getDisplayName(), savedUser.getRoles(),
                        savedUser.getAddress(), savedUser.getFinancialData(), savedUser.getContactInfo());
            }

        } else {
            Logger.getLogger("registrationLogger").log(Level.INFO, "Unsuccessful user registration! Invalid user group provided.");
            throw new RegistrationUnsuccessfulException("Unsuccessful user registration! Invalid user group provided.");
        }
    }

    private boolean isValidRole(String roleName) {
        Logger.getLogger("registrationLogger").log(Level.INFO, "Validating role name...." + roleName);
        return roleRepo.findRoleByRoleName(roleName).isPresent();
    }

    private boolean doesUserExist(RegistrationMessage message) {
        return userRepo.findActiveUserByLoginname(message.getLoginname()).isPresent();
    }

    public UserResponse getUserById(Long userId) throws LoginUnsuccessfulException {
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
                        setToList(savedUser.get().getRoles()),//TODO to check how Jackson serializes sets
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
        return new ArrayList<>(set);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findActiveUserByLoginname(username).orElseThrow(() -> new NoSuchElementException("Bad credentials!"));
    }

}
