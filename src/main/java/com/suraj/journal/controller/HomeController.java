package com.suraj.journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home(){
        return "Okay";
    }

    @GetMapping("/health")
    public String healthCheck(){
        return "It's running good!";
    }
}
