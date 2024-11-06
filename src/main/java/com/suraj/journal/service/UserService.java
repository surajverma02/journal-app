package com.suraj.journal.service;

import com.suraj.journal.entity.Entry;
import com.suraj.journal.entity.User;
import com.suraj.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
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

    public Boolean deleteUserById(ObjectId id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User updateUser(ObjectId id, User user){
        try {
            Optional<User> userData = userRepository.findById(id);
            if (userData.isPresent()){
                User updatedUser = userData.get();
                updatedUser.setUsername(user.getUsername());
                updatedUser.setPassword(user.getPassword());
                return userRepository.save(updatedUser);
            }
            return null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
