package com.niculescu.rest.webservices.restfulwebservice.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {

  private static final long serialVersionUID = -5616176897013108345L;

  private String username;
    private String password;

//    {
//        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsYXUiLCJleHAiOjE1OTU2Mzg2MjMsImlhdCI6MTU5NTYwMjYyM30.BOUWl6oZNTsGXbhhsTjlgvu8gsks5UBViIFzv0Ncbl-p6bPxpNZ5Z71vQFVk230LfqveULAEjOQ_VdLPNVdSpw"
//    }

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
