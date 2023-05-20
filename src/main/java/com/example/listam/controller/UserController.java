package com.example.listam.controller;

import com.example.listam.entity.User;
import com.example.listam.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    @GetMapping("/register")
    public String registerPage(){
        return"register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Optional<User> user1 = userRepository.fundByEmail(user.getEmail());
        if (user1.isEmpty()) {
            String password = user.getPassword();
            String encode = passwordEncoder.encode(password);
            user.setPassword(encode);
            userRepository.save(user);
        }
        return "redirect:/";
    }

}
