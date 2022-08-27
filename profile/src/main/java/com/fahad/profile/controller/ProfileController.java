package com.fahad.profile.controller;

import com.fahad.profile.domain.User;
import com.fahad.profile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findAllUsers(){
       return userRepository.findAll();
    }

    @PostMapping("/user")
    public void newUser(@RequestBody User user){
       userRepository.save(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user){
      userRepository.save(user);
    }
}
