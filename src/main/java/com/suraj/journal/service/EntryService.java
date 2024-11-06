package com.suraj.journal.service;

import com.suraj.journal.entity.Entry;
import com.suraj.journal.repository.EntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public Entry createEntry(Entry entry){
        return entryRepository.save(entry);
    }

    public List<Entry> getAllEntry(){
        return entryRepository.findAll();
    }

    public Entry getEntryById(ObjectId id){
        try {
            Optional<Entry> entryData = entryRepository.findById(id);
            if (entryData.isPresent()) {
                return entryData.get();
            }
            return null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean deleteEntryById(ObjectId id){
        try {
            entryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Entry updateEntry(ObjectId id, Entry entry){
        try {
            Optional<Entry> entryData = entryRepository.findById(id);
            if (entryData.isPresent()){
                Entry updatedEntry = entryData.get();
                updatedEntry.setTitle(entry.getTitle());
                updatedEntry.setContent(entry.getContent());
                return entryRepository.save(updatedEntry);
            }
            return null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
