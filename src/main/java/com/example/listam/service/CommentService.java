package com.example.listam.service;

import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.entity.User;

import java.util.Optional;

public interface CommentService {
    void save(Comment comment, User currentUser);

    Optional<Item> findAllByItem_Id(int id);
}
