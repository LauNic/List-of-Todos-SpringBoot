package com.niculescu.rest.webservices.restfulwebservice.helloWorld;

import org.springframework.web.bind.annotation.*;

//controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HelloWorldController {


    @GetMapping(path = "/hello-world")
    public String helloWorld() {

        return "Hello World!";
    }

    //hello-world-bean
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {

        return new HelloWorldBean("Hello World Bean!");
//        throw new RuntimeException("Some error has happened! Contact support ...");
    }

    //hello-world-bean/path-variable/Name
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {

        return new HelloWorldBean(String.format("Hello World, %s!", name));
    }
}
