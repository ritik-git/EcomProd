package com.telusko.SpringEcom.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String greet() {
        return "Welcome to Ritik";
    }
    
 
}
