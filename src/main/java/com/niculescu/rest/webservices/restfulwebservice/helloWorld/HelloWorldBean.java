package com.niculescu.rest.webservices.restfulwebservice.helloWorld;

public class HelloWorldBean {

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

    public HelloWorldBean(String s) {
        this.message = s;
    }
}
