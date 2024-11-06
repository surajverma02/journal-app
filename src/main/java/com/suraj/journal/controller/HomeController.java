package com.suraj.journal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Okay", HttpStatus.OK);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("It's running good!", HttpStatus.OK);
    }
}
