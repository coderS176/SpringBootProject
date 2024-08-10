package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepo userEntryRepo;

    public void saveEntry(User user) {
        userEntryRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userEntryRepo.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id) {
        userEntryRepo.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userEntryRepo.findByUserName(userName);
    }
}
