package com.mihir.MyFinace.controllers;

import com.mihir.MyFinace.models.Transaction;
import com.mihir.MyFinace.models.User;
import com.mihir.MyFinace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User authenticatedUser = userService.findByUsername(user.getUsername());

        if (authenticatedUser != null && userService.authenticateUser(user.getUsername(), user.getPassword())) {
            return ResponseEntity.ok(authenticatedUser); // ✅ Return full user details
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping("/user/name/{name}")
    public User getUserByName(@PathVariable String name){
        return userService.getUserByName(name);
    }
    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @PostMapping("/{userId}/transactions")
    public User addTransaction(@PathVariable String userId, @RequestBody Transaction transaction) {
        return userService.addTransaction(userId, transaction);
    }

    @DeleteMapping("/{userId}/transactions/{txnId}")
    public User deleteTransaction(@PathVariable String userId, @PathVariable String txnId) {
        return userService.deleteTransaction(userId, txnId);
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserDetails(@PathVariable String userId) {
        return userService.getUserById(userId);
    }
}