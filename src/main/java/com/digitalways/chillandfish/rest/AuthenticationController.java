/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.rest;

import com.digitalways.chillandfish.api.ApiResponse;
import com.digitalways.chillandfish.messages.LoginMessage;
import com.digitalways.chillandfish.messages.LoginResponse;
import com.digitalways.chillandfish.security.AuthenticationService;
import com.digitalways.chillandfish.security.AuthorizationService;
import com.digitalways.chillandfish.security.UnsuccesfulLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author huba
 */
@RestController
@RequestMapping(value = "public/auth/")
public class AuthenticationController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(consumes = {"application/json"})
    ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginMessage pLoginMessage) {
        ArrayList<String> errors = new ArrayList<>();
        try {

            if (authorizationService.checkApiKey(request)) {

                if (authorizationService.acceptsJson(request)) {
                    try {
                        return new ResponseEntity<>(new ApiResponse("OK", null, authenticationService.login(pLoginMessage)), HttpStatus.OK);

                    } catch (RuntimeException r) {
                        r.printStackTrace();
                        throw new UnsuccesfulLoginException(r.getMessage());
                    }
                } else {
                    errors.add("Unsupported response format! Only Json is supported");
                    return new ResponseEntity<>(new ApiResponse<>("Failed", errors, null), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
                }

            } else {
                errors.add("Not authorized to access api");
                return new ResponseEntity<>(new ApiResponse<>("Failed", errors, null), HttpStatus.UNAUTHORIZED);
            }
        } catch (RuntimeException r) {
            r.printStackTrace();
            errors.add(r.getMessage());
            return new ResponseEntity<>(new ApiResponse<>("Error", errors, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "refreshCredentials/")
    ResponseEntity<ApiResponse<LoginResponse>> refreshCredentials(@RequestParam String oldToken) {
        //TODO handle credential change, deliver new token in the response body
        return null;
    }

    @RequestMapping(value = "logout/{userID}")
    ResponseEntity<ApiResponse<Void>> logout(@PathVariable Long userId, String roleName) {
        //TODO handle session destruction, logout,
        return null;
    }


}
