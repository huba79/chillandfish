package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.persistence.Role;
import com.digitalways.chillandfish.persistence.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RequestValidatorService {
    static String apiKey;
    private final Logger logger= Logger.getLogger("requestValidator");

    public Boolean checkApiKey(HttpServletRequest request) {

        if (!request.getHeader("x-api-key").isEmpty()) {
            logger.log(Level.INFO,"Delivered apikey: " + request.getHeader("x-api-key"));
            logger.log(Level.INFO,"Expected apikey: " + apiKey);
            return request.getHeader("x-api-key").equals(apiKey);
        } else return false;
    }

    public Boolean acceptsJson(HttpServletRequest request) {
        if (request.getHeader("Accept").equals("application/json")) return true;
        else {
            logger.log(Level.INFO,request.getHeader("Accept"));
            return false;
        }
    }

    @Value("${server.deployment.apikey}")
    public void setApiKey(String apiKey) {
        RequestValidatorService.apiKey = apiKey;
    }

    //TODO to include specific rights after rights system is implemented
    public boolean confirmProvidedRole(User user, @NotNull String providedRoleName) {
        boolean isRoleValid = false;
        for (Role role : user.getRoles()) {
            if (role.getRoleName().equals(providedRoleName)) {
                return true;
            }
        }
        return isRoleValid;
    }

}
