package com.digitalways.chillandfish.authentication.rest;

import com.digitalways.chillandfish.authentication.messages.RegistrationResponse;
import com.digitalways.chillandfish.authentication.messages.RegistrationMessage;
import com.digitalways.chillandfish.api.ApiMessage;
import com.digitalways.chillandfish.api.ApiResponse;
import javax.validation.Valid;

import com.digitalways.chillandfish.authentication.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author huba
 */
@RestController
public class RegistrationController {

    @Autowired RegistrationService registrationService;

        @RequestMapping(value = "{apiVersion}/registration/",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<ApiResponse<RegistrationResponse>> registration(@Valid @RequestBody ApiMessage<RegistrationMessage> body,
                                                            @PathVariable("apiVersion") String apiVersion ){//ha minden OK
        return new ResponseEntity<>(
                new ApiResponse<>("OK",null,registrationService.createUser(body.getData())),
                HttpStatus.ACCEPTED) ;
    }
}
