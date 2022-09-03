package com.fahad.lendingengine.event;

import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.repository.UserRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {
    @Autowired
    private UserRepository userRepository;
    private static final Gson GSON = new Gson();
    private Logger LOGGER = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

    public void handleUserRegistration(String userDetails){
       User user =  GSON.fromJson(userDetails, User.class);
       LOGGER.info("user {} registered", user.getUsername());
       userRepository.save(user);
    }
}
