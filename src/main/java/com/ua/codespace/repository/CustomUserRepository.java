package com.ua.codespace.repository;

import com.ua.codespace.model.User;


public interface CustomUserRepository {
    User findByUsername(String username);
}
