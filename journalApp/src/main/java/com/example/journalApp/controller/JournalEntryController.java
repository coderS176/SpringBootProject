package com.example.journalApp.controller;

import com.example.journalApp.JournalApplication;
import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    //    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAllEntries")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntry();
    }

    @PostMapping("/postEntry")
    public boolean createEntry(@RequestBody JournalEntry j_entry) {
        j_entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(j_entry);
        return true;
    }

    @GetMapping("/getJournalById/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getById(id).orElse(null);
    }

    @DeleteMapping("/DeleteJournalByID/{id}")
    public void deleteJournalById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
    }

    @PutMapping("UpdateJournalEntry/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryService.getById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }
}
