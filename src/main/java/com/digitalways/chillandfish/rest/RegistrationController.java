package com.digitalways.chillandfish.rest;

import com.digitalways.chillandfish.api.ApiResponse;
import com.digitalways.chillandfish.messages.RegistrationMessage;
import com.digitalways.chillandfish.messages.RegistrationResponse;
import com.digitalways.chillandfish.security.AuthorizationService;
import com.digitalways.chillandfish.security.RegistrationService;
import com.digitalways.chillandfish.security.RegistrationUnsuccessfulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huba
 */
@RestController(value = "public/registration/")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    AuthorizationService authService;

    @RequestMapping(consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<ApiResponse<RegistrationResponse>> registration(@Valid @RequestBody RegistrationMessage body) throws RegistrationUnsuccessfulException {
        List<String> errors = new ArrayList<>();
        if (!authService.checkApiKey(request)) {
            errors.add("Invalid Api Key");
            if (!authService.acceptsJson(request)) {
                errors.add("Invalid message format expected!");
            }
            return new ResponseEntity<>(
                    new ApiResponse<>("Error", errors, null),
                    HttpStatus.BAD_REQUEST);
        } else {
            try {
                return new ResponseEntity<>(
                        new ApiResponse<>("OK", null, registrationService.createUser(body)),
                        HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                errors.add(e.getMessage());
                return new ResponseEntity<>(
                        new ApiResponse<>("Error", errors, null),
                        HttpStatus.CREATED);
            }
        }

    }
}
