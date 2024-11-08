package com.suraj.journal.service;

import com.suraj.journal.entity.User;
import com.suraj.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User createAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(ObjectId id){
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()){
                return userData.get();
            }
            return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Boolean deleteUserByUsername(String username){
        try {
            User user = userRepository.findByUsername(username);
            userRepository.delete(user);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User updateUser(String username, User user){
        try {
            User userData = userRepository.findByUsername(username);
            userData.setUsername(user.getUsername());
            userData.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(userData);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
