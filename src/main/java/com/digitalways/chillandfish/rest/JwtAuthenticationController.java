package com.digitalways.chillandfish.rest;

import com.digitalways.chillandfish.messages.AuthenticationJwtResponse;
import com.digitalways.chillandfish.messages.AuthenticationMessage;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.security.JwtTokenUtil;
import com.digitalways.chillandfish.services.UsersService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
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

    final Logger logger = Logger.getLogger(JwtAuthenticationController.class.getName());

    @RequestMapping(value = "/public/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationMessage authenticationRequest) throws Exception {
        logger.log(Level.INFO,"Trying to authenticate...");

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        logger.log(Level.INFO,"Authentication successful...");

        final User user = usersService.loadUserByUsername(authenticationRequest.getUsername());
        logger.log(Level.INFO,"User successfully identified...");

        final String token = jwtTokenUtil.generateToken(user);
        logger.log(Level.INFO,"Token generated: "+token+" , returning response...");

        return ResponseEntity.ok(new AuthenticationJwtResponse(token));
    }

    @RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return ResponseEntity.ok(new AuthenticationJwtResponse(token));
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
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