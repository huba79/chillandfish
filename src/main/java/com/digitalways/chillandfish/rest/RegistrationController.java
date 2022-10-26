package com.digitalways.chillandfish.rest;

import com.digitalways.chillandfish.api.ApiResponse;
import com.digitalways.chillandfish.messages.RegistrationMessage;
import com.digitalways.chillandfish.messages.RegistrationResponse;
import com.digitalways.chillandfish.security.RequestValidatorService;
import com.digitalways.chillandfish.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author huba
 */
@RestController
public class RegistrationController {

    @Autowired
    UsersService usersService;
    @Autowired
    HttpServletRequest request;
    Logger logger = Logger.getLogger("RegistrationController");

    @PostMapping(value = "public/registration/", produces = APPLICATION_JSON_VALUE)
        //@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<ApiResponse<RegistrationResponse>> registration(@RequestBody RegistrationMessage body) {
        List<String> errors = new ArrayList<>();
        Logger.getLogger("registrationLogger").log(Level.INFO, "Authentication:.." + request.getAuthType() + "\n");

            try {
                logger.log(Level.FINEST, "Message body:..." + body);
                return new ResponseEntity<>(
                        new ApiResponse<>("OK", null, usersService.createUser(body)),
                        HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                errors.add(e.getMessage());
                return new ResponseEntity<>(
                        new ApiResponse<>("Error", errors, null),
                        HttpStatus.CONFLICT);
            }


    }
}
