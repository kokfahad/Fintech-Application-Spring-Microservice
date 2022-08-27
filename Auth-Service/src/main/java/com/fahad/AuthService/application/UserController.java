package com.fahad.AuthService.application;

import com.fahad.AuthService.exception.UserNotFoundException;
import com.fahad.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/validate")
    public String validateTokenAndGetUsername(@RequestHeader("Authorization")
                                                  String token){
        return userRepository.findById(token).orElseThrow(()-> new UserNotFoundException())
                .getUsername();
    }
}
