package com.niculescu.rest.basicAuth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthenticationController {

    //hello-world-bean
    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicAuth() {

        return new AuthenticationBean("You are authenticated!");
    }

}
