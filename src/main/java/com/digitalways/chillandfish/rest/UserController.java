package com.digitalways.chillandfish.rest;

import com.digitalways.chillandfish.api.ApiResponse;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.security.UserNotFoundException;
import com.digitalways.chillandfish.security.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "protected/users/{id}",
            method = RequestMethod.GET)
    ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        Logger.getLogger("usersApiGetLogger").log(Level.INFO,"GetUser By Id: "+id.toString()+" called....");
        try {
            return new ResponseEntity<>(new ApiResponse("OK", null, usersService.getUserById(id)), HttpStatus.OK);

        } catch (RuntimeException r) {
            Logger.getLogger("usersApiGetLogger").log(Level.INFO,"GetUser By Id: "+id.toString()+" failed....");
            r.printStackTrace();
            throw new UserNotFoundException(r.getMessage());
        }

    }
}
