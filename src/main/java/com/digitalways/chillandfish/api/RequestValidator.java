package com.digitalways.chillandfish.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestValidator {

    static String apiKey;
    public static Boolean checkApiKey(HttpServletRequest request){

        if(!request.getHeader("x-api-key").isEmpty()) {
            System.out.println("Delivered apikey: "+ request.getHeader("x-api-key"));
            System.out.println("Expected apikey: "+ apiKey);
            return request.getHeader("x-api-key").equals(apiKey);
        } else return false;
    };

    public static Boolean acceptsJson(HttpServletRequest request){
        if(request.getHeader("Accept").equals("application/json") ) return true;
            else {
            request.getHeader("Accept");
                return false;
            }
    };

    @Value("${server.deployment.apikey}")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
