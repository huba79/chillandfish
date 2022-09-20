package com.digitalways.chillandfish.authentication.rest;

import com.digitalways.chillandfish.api.ApiResponse;
import com.digitalways.chillandfish.authentication.messages.UserResponse;
import com.digitalways.chillandfish.authentication.persistence.User;
import com.digitalways.chillandfish.authentication.services.UserNotFoundException;
import com.digitalways.chillandfish.authentication.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UsersService usersService;
    @RequestMapping(value = "authenticated/users/{id}",
            method = RequestMethod.GET)
    ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(new ApiResponse("OK",null,usersService.getUserById(id)), HttpStatus.OK);
        } catch (RuntimeException r){
            r.printStackTrace();
            throw new UserNotFoundException(r.getMessage());
        }

    }
}
