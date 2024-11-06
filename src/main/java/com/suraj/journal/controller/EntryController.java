package com.suraj.journal.controller;

import com.suraj.journal.entity.Entry;
import com.suraj.journal.service.EntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping("/add/{username}")
    public ResponseEntity<Entry> createEntry(@RequestBody Entry entry, @PathVariable String username){
        try {
            return new ResponseEntity<>(entryService.createEntry(entry, username),HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all/{username}")
    public ResponseEntity<List<Entry>> getAllEntryOfUser(@PathVariable String username){
        try {
            return new ResponseEntity<>(entryService.getAllEntryOfUser(username),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Entry>> getAllEntry(){
        try {
            return new ResponseEntity<>(entryService.getAllEntry(),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable ObjectId id, @PathVariable String username){
        try {
            return new ResponseEntity<>(entryService.getEntryById(id, username),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEntryById(@PathVariable ObjectId id, @PathVariable String username){
        try {
            return new ResponseEntity<>(entryService.deleteEntryById(id, username),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable ObjectId id, @RequestBody Entry entry, @PathVariable String username){
        try {
            return new ResponseEntity<>(entryService.updateEntry(id, entry, username),HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
