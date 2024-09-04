package com.example.usermanagement.service;

//src/main/java/com/example/usermanagement/service/UserService.java



import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

 @Autowired
 private UserRepository userRepository;

 // Create a new user
 public User createUser(User user) {
     return userRepository.save(user);
 }

 // Get all users
 public List<User> getAllUsers() {
     return userRepository.findAll();
 }

 // Get user by ID
 public Optional<User> getUserById(Long id) {
     return userRepository.findById(id);
 }

 // Update user
 public User updateUser(Long id, User userDetails) {
     User user = userRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("User not found with id " + id));
     user.setName(userDetails.getName());
     user.setEmail(userDetails.getEmail());
     user.setRole(userDetails.getRole());
     return userRepository.save(user);
 }

 // Delete user
 public void deleteUser(Long id) {
     User user = userRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("User not found with id " + id));
     userRepository.delete(user);
 }
}
