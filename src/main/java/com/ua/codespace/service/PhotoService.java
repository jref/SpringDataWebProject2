package com.ua.codespace.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    String saveUserPhoto(String username, MultipartFile image);

}
