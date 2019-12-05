package com.example.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/user")
    public  String user(){
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
