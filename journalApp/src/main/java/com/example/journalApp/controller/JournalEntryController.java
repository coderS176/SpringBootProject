package com.example.journalApp.controller;

import com.example.journalApp.JournalApplication;
import com.example.journalApp.entity.JournalEntry;
import com.example.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    //    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getAllEntries")
    public ResponseEntity<List<JournalEntry>> getAll() {
        List<JournalEntry> all_entry = journalEntryService.getAllEntry();
        if (all_entry.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all_entry, HttpStatus.OK);
    }

    @PostMapping("/postEntry")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry j_entry) {
        j_entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(j_entry);
        return new ResponseEntity<>(j_entry, HttpStatus.CREATED);
    }

    @GetMapping("/getJournalById/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getById(id).orElse(null);
    }

    @DeleteMapping("/DeleteJournalByID/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id) {
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("UpdateJournalEntry/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryService.getById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
