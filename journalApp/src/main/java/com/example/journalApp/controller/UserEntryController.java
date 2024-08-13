package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.service.QuoteService;
import com.example.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private QuoteService quoteService;

//    GetAll user and getUserById ki functionality user ko nhi rhegi!!
/*  @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> all_users = userEntryService.getAllUsers();
        if (all_users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all_users, HttpStatus.OK);
    }

    @GetMapping("/getUserById:{id}")
    public ResponseEntity<?> getUser(@PathVariable ObjectId id) {
        return new ResponseEntity<>(userEntryService.getUserById(id), HttpStatus.OK);
    }

 */

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userEntryService.findByUserName(userName);
        userEntryService.deleteById(user.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping

    /*  There are so many logical errors in this part of code. ALL the Errors and Exceptions needs to get resolved soon!*/
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userEntryService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(newUser.getUserName());
            userInDb.setPassword(newUser.getPassword());
            userEntryService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public String greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String q = quoteService.getQuote();
        String response = "Hi, " + userName + " " + q;
        return response;
    }
}
