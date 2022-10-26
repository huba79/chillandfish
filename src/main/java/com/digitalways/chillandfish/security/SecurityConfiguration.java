package com.digitalways.chillandfish.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
// it remains to see if we really need EnableWebSecurity in api only phase,
//might be needed during web backoffice development
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    @Autowired
    AuthenticationErrorHandler authenticationErrorHandler;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    Logger logger = Logger.getLogger(SecurityConfiguration.class.getName());
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.log(Level.INFO, "Entering FilterChain. ");

        http
            .csrf().disable()
//      set up csrf protection
//         .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests()
                .antMatchers("/public/**")
                .permitAll()
            .and()
                .exceptionHandling().authenticationEntryPoint(authenticationErrorHandler)
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                ;

        logger.log(Level.INFO, "Exiting FilterChain. ");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
            String idForEncode = "bcrypt";
            Map<String, PasswordEncoder> encoders = new HashMap<>();
            encoders.put(idForEncode, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
