
//src/main/java/com/example/usermanagement/controller/UserController.java

package com.example.usermanagement.controller;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

 @Autowired
 private UserService userService;

 // Create a new user
 @PostMapping
 public User createUser(@RequestBody User user) {
     return userService.createUser(user);
 }

 // Get all users
 @GetMapping
 public List<User> getAllUsers() {
     return userService.getAllUsers();
 }

 // Get user by ID
 @GetMapping("/{id}")
 public ResponseEntity<User> getUserById(@PathVariable Long id) {
     return userService.getUserById(id)
             .map(ResponseEntity::ok)
             .orElse(ResponseEntity.notFound().build());
 }

 // Update user
 @PutMapping("/{id}")
 public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
     try {
         User updatedUser = userService.updateUser(id, userDetails);
         return ResponseEntity.ok(updatedUser);
     } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
     }
 }

 // Delete user
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
     try {
         userService.deleteUser(id);
         return ResponseEntity.noContent().build();
     } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
     }
 }
}
