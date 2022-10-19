package com.digitalways.chillandfish.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
// it remains to see if we really need EnableWebSecurity in api only phase,
//migth be needed during web backoffice development
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    AuthenticationService authenticationService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String,PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
                            encoders.put("noop", NoOpPasswordEncoder.getInstance());
                    //        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
                    //        encoders.put("scrypt", new SCryptPasswordEncoder());
                    //        encoders.put("sha256", new StandardPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        return passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/public/*")
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/protected/*")
                .hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/admin/*")
                .hasAnyRole("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//TODO to learn more about keys

//                .and()
//                .rememberMe()
//                    .tokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(30))
//                    .key("somethingverysecured")
//                    .rememberMeParameter("remember-me")
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
