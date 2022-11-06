package com.digitalways.chillandfish.rest;

import com.digitalways.chillandfish.api.ApiResponse;
import com.digitalways.chillandfish.messages.DummyMessage;
import com.digitalways.chillandfish.messages.DummyResponse;
import com.digitalways.chillandfish.messages.UserMessage;
import com.digitalways.chillandfish.messages.UserResponse;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.security.exceptions.UserNotFoundException;
import com.digitalways.chillandfish.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    UsersService usersService;
    private final Logger logger = Logger.getLogger(UserController.class.getName());

    @RequestMapping(value = "protected/users/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMINS','ROLE_USERS')")
    ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        logger.log(Level.INFO, "GetUser By Id: " + id.toString() + " called....");
        try {
            UserResponse userResponse = usersService.getUserById(id);
            if (userResponse != null)
                return new ResponseEntity<>(new ApiResponse("OK", null, usersService.getUserById(id)), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ApiResponse("OK", null, usersService.getUserById(id)), HttpStatus.NO_CONTENT);
        } catch (RuntimeException r) {
            logger.log(Level.INFO, "GetUser By Id: " + id + " failed....");
            r.printStackTrace();
            throw new UserNotFoundException(r.getMessage());
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMINS')")
    @RequestMapping(value = "admin/dummy", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
            ResponseEntity<ApiResponse<DummyResponse>> getAdminstuff (@RequestBody DummyMessage body){
        return new ResponseEntity<>( new ApiResponse<>("OK"  ,
                                    null,
                                    new DummyResponse("OK : " + body.getDummyMsg())
                                    ),
                                    HttpStatus.OK);
    }

    @RequestMapping(value = "protected/users/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_ADMINS','ROLE_USERS')")
    ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserMessage updatedUserMessage){
        usersService.saveUser(updatedUserMessage);
        return  ResponseEntity.ok().build();
    }
}
