package com.suraj.journal.controller;

import com.suraj.journal.entity.Entry;
import com.suraj.journal.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping("/add")
    public Entry addEntry(@RequestBody Entry entry){
        return entryService.addEntry(entry);
    }

    @GetMapping("/get-all")
    public List<Entry> getAllEntry(){
        return entryService.getAllEntry();
    }

    @GetMapping("/get/{id}")
    public Entry getById(@PathVariable Long id){
        return entryService.getEntryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id){
        return entryService.deleteEntry(id);
    }

    @PutMapping("/update/{id}")
    public Entry updateEntry(@PathVariable Long id, @RequestBody Entry entry){
        return entryService.updateEntry(id, entry);
    }
}
