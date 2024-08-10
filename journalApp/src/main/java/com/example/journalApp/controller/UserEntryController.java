package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> all_users = userEntryService.getAllUsers();
        if (all_users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all_users, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userEntryService.saveEntry(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getUserById:{id}")
    public ResponseEntity<?> getUser(@PathVariable ObjectId id) {
        return new ResponseEntity<>(userEntryService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById:{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id) {
        userEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/updateUser:{userName}")
    public ResponseEntity<?> updateUserById(@PathVariable String userName, @RequestBody User newUser) {
        User userInDb = userEntryService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(newUser.getUserName());
            userInDb.setPassword(newUser.getPassword());
            userEntryService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
