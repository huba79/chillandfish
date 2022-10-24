package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UsersService usersService;

    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        logger.log(Level.INFO,"Trying to authenticate...");
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        logger.log(Level.INFO,"Authentication successful...");
        final UserDetails userDetails = usersService
                .loadUserByUsername(authenticationRequest.getUsername());
        logger.log(Level.INFO,"User successfully identified...");

        final String token = jwtTokenUtil.generateToken(userDetails);
        logger.log(Level.INFO,"Token generated...returning response...");

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }  catch (LockedException e) {
            throw new Exception("USER_LOCKED", e);
        } catch (CredentialsExpiredException e) {
            throw new Exception("CREDENTIALS_EXPIRED", e);
        }
    }
}