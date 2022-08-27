/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.rest;

import com.digitalways.chillandfish.authentication.messages.LoginResponse;
import com.digitalways.chillandfish.authentication.messages.LoginMessage;
import com.digitalways.chillandfish.api.ApiMessage;
import com.digitalways.chillandfish.api.ApiResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author huba
 */
@RestController
public class AuthController {
        @RequestMapping(value = "login/",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody ApiMessage<LoginMessage> body){
        return null;
    }
}
