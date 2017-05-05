package com.ua.codespace.exception;

public class UserNotFoundException extends RuntimeException {
    private String username;

    public UserNotFoundException(Long id) {
        super("User not found! [id=" + id + "]");
        this.username = username;
    }
}
