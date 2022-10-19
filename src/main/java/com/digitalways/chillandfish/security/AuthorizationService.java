package com.digitalways.chillandfish.security;

import com.digitalways.chillandfish.messages.AuthToken;
import com.digitalways.chillandfish.persistence.Role;
import com.digitalways.chillandfish.persistence.Session;
import com.digitalways.chillandfish.persistence.User;
import com.digitalways.chillandfish.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class AuthorizationService {
    @Autowired
    SessionRepository sessionRepo;
    static String apiKey;

    public Boolean checkApiKey(HttpServletRequest request) {

        if (!request.getHeader("x-api-key").isEmpty()) {
            System.out.println("Delivered apikey: " + request.getHeader("x-api-key"));
            System.out.println("Expected apikey: " + apiKey);
            return request.getHeader("x-api-key").equals(apiKey);
        } else return false;
    }

    public Boolean acceptsJson(HttpServletRequest request) {
        if (request.getHeader("Accept").equals("application/json")) return true;
        else {
            System.out.println(request.getHeader("Accept"));
            return false;
        }
    }

    @Value("${server.deployment.apikey}")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    //TODO to include specific rights after rights system is implemented
    public boolean confirmProvidedRole(User user, @NotNull String providedRoleName) {
        boolean isRoleValid = false;
        for (Role role : user.getRoles()) {
            if (role.getRoleName().equals(providedRoleName)) {
                isRoleValid = true;
                return isRoleValid;
            }
        }
        return isRoleValid;
    }

    public AuthToken authorize(User user, Role currentRole) {
        try {
            if (currentRole != null) {
                String sessionID = UUID.randomUUID().toString();
                Session savedSession = sessionRepo.save(new Session(user, currentRole, sessionID));
                //TODO szet kell majd valasztani a sessiont a tokentol
                return new AuthToken(savedSession.getSessionId(), true, savedSession.getSessionId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsuccesfulLoginException(e.getMessage());
        }
        throw new UnsuccesfulLoginException("Login unsuccessful. No role provided.");
    }

//    public AuthToken refresh(Long userId, String roleName, AuthToken oldToken){
//        return oldToken; //TODO design and implement access token refresh
//    }


}
