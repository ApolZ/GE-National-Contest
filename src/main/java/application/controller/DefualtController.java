package application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AZ on 2016/4/26.
 */

@RestController
public class DefualtController {

    @RequestMapping(value = "/verify_password", method = RequestMethod.POST)
    public String verifyPassword(String username, String password) {
        System.out.println("username: "+username);
        return "{\"Result\":\"True\",\"Position\":\"Client\"}";
    }
}
