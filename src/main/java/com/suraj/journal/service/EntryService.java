package com.suraj.journal.service;

import com.suraj.journal.entity.Entry;
import com.suraj.journal.entity.User;
import com.suraj.journal.repository.EntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Entry createEntry(Entry entry, String username){
        try {
            User user = userService.getUserByUsername(username);
            Entry savedEntry = entryRepository.save(entry);
            user.getEntries().add(savedEntry);
            userService.saveUser(user);
            return entryRepository.save(entry);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error while creating entry!");
        }
    }

    public List<Entry> getAllEntryOfUser(String username){
        try {
            User user = userService.getUserByUsername(username);
            return user.getEntries();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Entry> getAllEntry(){
        return entryRepository.findAll();
    }

    public Entry getEntryById(ObjectId id, String username){
        try {
            Optional<Entry> entryData = entryRepository.findById(id);
            if (entryData.isPresent()) {
                User user = userService.getUserByUsername(username);
                if(user.getEntries().contains(entryData.get())){
                    return entryData.get();
                }
            }
            throw new RuntimeException("Unauthorized operation!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Boolean deleteEntryById(ObjectId id, String username){
        try {
            Optional<Entry> entryData = entryRepository.findById(id);
            if (entryData.isPresent()) {
                User user = userService.getUserByUsername(username);
                if(user.getEntries().contains(entryData.get())){
                    user.getEntries().remove(entryData.get());
                    userService.saveUser(user);
                    entryRepository.deleteById(id);
                    return true;
                }
            }
            throw new RuntimeException("Unauthorized operation!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error while creating entry!");
        }
    }

    @Transactional
    public Entry updateEntry(ObjectId id, Entry entry, String username){
        try {
            Optional<Entry> entryData = entryRepository.findById(id);
            if (entryData.isPresent()) {
                User user = userService.getUserByUsername(username);
                if(user.getEntries().contains(entryData.get())){
                    Entry updatedEntry = entryData.get();
                    updatedEntry.setTitle(entry.getTitle());
                    updatedEntry.setContent(entry.getContent());
                    return entryRepository.save(updatedEntry);
                }
            }
            throw new RuntimeException("Unauthorized operation!");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error while creating entry!");
        }
    }
}
