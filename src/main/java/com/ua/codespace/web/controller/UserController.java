package com.ua.codespace.web.controller;

import com.ua.codespace.exception.UserNotFoundException;
import com.ua.codespace.model.User;
import com.ua.codespace.repository.UserRepository;
import com.ua.codespace.service.PhotoService;
import com.ua.codespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    PhotoService photoService;

    @RequestMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping("/{id}")
    public String get(@PathVariable Long id, Model model) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        } else {
            model.addAttribute(user);
            return "user";
        }
    }

    @RequestMapping("/{id}/friends")
    public String getUserFriends(@PathVariable Long id, Model model) {
        model.addAttribute(userService.getUserFriends(id));
        return "users";
    }

    @RequestMapping("/add")
    public String getUserForm() {
        return "userForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(@Valid User user, @RequestParam("photo") MultipartFile image) {
        userService.save(user, image);
        return "redirect:/users";
    }

    @RequestMapping("/search/{username}")
    public String findUserByUsername(@PathVariable String username, Model model) {
        model.addAttribute(userRepository.findByUsername(username));

        return "user";
    }
}
