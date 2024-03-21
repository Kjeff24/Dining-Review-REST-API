package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{displayName}")
    public ResponseEntity<?> getUser(@PathVariable String displayName) {

        Optional<User> optionalExistingUser = userRepository.findUserByDisplayName(displayName);
        if (optionalExistingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with username " + displayName + " not found");
        }

        User existingUser = optionalExistingUser.get();
        existingUser.setId(null);

        return ResponseEntity.ok(existingUser);
    }

    @PutMapping("/{displayName}")
    public ResponseEntity<?> updateUserInfo(@PathVariable String displayName, @RequestBody User updatedUser) {

        Optional<User> optionalExistingUser = userRepository.findUserByDisplayName(displayName);
        if (optionalExistingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with username " + displayName + " not found");
        }
        if(updatedUser.getDisplayName() != null && !updatedUser.getDisplayName().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Permission Denied: Username " + displayName + " cannot be changed");
        }

        User existingUser = optionalExistingUser.get();


        copyUserInfoFrom(updatedUser, existingUser);
        userRepository.save(existingUser);
        existingUser.setId(null);
        return ResponseEntity.ok(existingUser);
    }

    private void copyUserInfoFrom(User updatedUser, User existingUser) {

        if (!ObjectUtils.isEmpty(updatedUser.getCity())) {
            existingUser.setCity(updatedUser.getCity());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getState())) {
            existingUser.setState(updatedUser.getState());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getZipCode())) {
            existingUser.setZipCode(updatedUser.getZipCode());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getPeanutAllergy())) {
            existingUser.setPeanutAllergy(updatedUser.getPeanutAllergy());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getDairyAllergy())) {
            existingUser.setDairyAllergy(updatedUser.getDairyAllergy());
        }

        if (!ObjectUtils.isEmpty(updatedUser.getEggAllergy())) {
            existingUser.setEggAllergy(updatedUser.getEggAllergy());
        }
    }



}
