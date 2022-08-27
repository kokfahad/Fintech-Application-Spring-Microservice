package com.fahad.AuthService.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Username Not Found");
    }
}
