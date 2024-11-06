package com.suraj.journal.controller;

import com.suraj.journal.entity.User;
import com.suraj.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAllUser(){
        try {
            return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId id){
        try {
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable ObjectId id){
        try {
            return new ResponseEntity<>(userService.deleteUserById(id),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User user){
        try {
            return new ResponseEntity<>(userService.updateUser(id, user),HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
