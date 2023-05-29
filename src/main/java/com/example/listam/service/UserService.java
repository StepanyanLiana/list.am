package com.example.listam.service;

import com.example.listam.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

   public void save(User user);
}
