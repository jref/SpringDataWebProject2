package com.ua.codespace.service;


import com.ua.codespace.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<User> getUserFriends(Long id);

    User save(User user, MultipartFile image);
}
