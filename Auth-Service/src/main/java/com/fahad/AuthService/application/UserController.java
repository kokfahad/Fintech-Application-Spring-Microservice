package com.fahad.AuthService.application;

import com.fahad.AuthService.User.model.User;
import com.fahad.AuthService.exception.UserNotFoundException;
import com.fahad.AuthService.repository.UserRepository;
import com.fahad.AuthService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private final NotificationService notificationService;

    @Autowired
    public UserController(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user){
        userRepository.save(user);
        notificationService.sendMessage(user);
    }

    @PostMapping("/validate")
    public String validateTokenAndGetUsername(@RequestHeader("Authorization")
                                                  String token){
        return userRepository.findById(token).orElseThrow(()-> new UserNotFoundException())
                .getUsername();
    }
}
