package com.example.listam.controller;

import com.example.listam.entity.User;
import com.example.listam.entity.UserType;
import com.example.listam.service.MailService;
import com.example.listam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final MailService mailService;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Optional<User> userFromDB = userService.findByEmail(user.getEmail());
        if (userFromDB.isEmpty()) {
            String password = user.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setUserType(UserType.USER);
            userService.save(user);
            //send email
            mailService.sendMail(user.getEmail(), "Welcome", "Hi" + user.getName() + "Welcome to our site!!!");
        }
        return "redirect:/";
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}

