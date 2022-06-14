package com.andre.test.api.easynotes.controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/token")
public class Oauth2Controller {

    @PostMapping
    public String sayHello() {
        return "Hello Im Andre";
    }
}
