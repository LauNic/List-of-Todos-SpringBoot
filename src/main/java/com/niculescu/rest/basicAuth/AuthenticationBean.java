package com.niculescu.rest.basicAuth;

public class AuthenticationBean {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

    private String message;

    public AuthenticationBean(String s) {
        this.message = s;
    }
}
