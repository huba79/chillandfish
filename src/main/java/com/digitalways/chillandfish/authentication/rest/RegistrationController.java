package com.digitalways.chillandfish.authentication.rest;

import com.digitalways.chillandfish.authentication.messages.RegistrationResponse;
import com.digitalways.chillandfish.authentication.messages.RegistrationMessage;
import com.digitalways.chillandfish.api.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.digitalways.chillandfish.authentication.services.AuthorizationService;
import com.digitalways.chillandfish.authentication.services.RegistrationUnsuccessfulException;
import com.digitalways.chillandfish.authentication.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huba
 */
@RestController
public class RegistrationController {

    @Autowired RegistrationService registrationService;
    @Autowired HttpServletRequest request;
    @Autowired
    AuthorizationService authService;
    @RequestMapping(value = "registration/",
    consumes = { "application/json" },
    method = RequestMethod.POST)
    ResponseEntity<ApiResponse<RegistrationResponse>> registration(@Valid @RequestBody RegistrationMessage body) throws RegistrationUnsuccessfulException {
            List<String> errors = new ArrayList<>();
            if (! authService.checkApiKey(request)) {
                errors.add("Invalid Api Key");
                if(!authService.acceptsJson(request) ) {
                    errors.add("Invalid message format expected!");
                }
                return new ResponseEntity<>(
                        new ApiResponse<>("Error",errors,null),
                        HttpStatus.BAD_REQUEST) ;
            } else {
                try {
                    return new ResponseEntity<>(
                            new ApiResponse<>("OK", null, registrationService.createUser(body)),
                            HttpStatus.CREATED);
                } catch (Exception e){
                    e.printStackTrace();
                    errors.add(e.getMessage());
                    return new ResponseEntity<>(
                            new ApiResponse<>("Error", errors, null),
                            HttpStatus.CREATED);
                }
            }

    }
}
