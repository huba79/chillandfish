package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.services.UsersService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws ServletException, IOException {
        boolean isTokenExpired = false;
        final String requestAuthorizationTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestAuthorizationTokenHeader != null && requestAuthorizationTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestAuthorizationTokenHeader.substring(7);
            try {
                //todo check why it still runs on ExpiredJwtException
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.log(Level.SEVERE,"Unable to get JWT Token");
            }  catch (MalformedJwtException e) {
                logger.log(Level.WARNING,"JWT Token has wrong format! check syntax.");
            } catch (ExpiredJwtException e) {
                isTokenExpired = true;
                //TODO issue new token
            }
        } else {
            logger.log(Level.SEVERE,"Incorrect authentication data. JWT Token does not begin with Bearer String");
        }
        // Once we get the token we check if authentication is null and then validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if(isTokenExpired)  logger.log(Level.WARNING,"JWT Token is expired");
            else logger.log(Level.INFO,"Token is valid....");

            User user = this.usersService.loadUserByUsername(username);
            // if token is valid configure Spring Security to manually set authentication
            if (jwtTokenUtil.validateToken(jwtToken, user)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify that the current user is authenticated.
                // So it passes the Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

}