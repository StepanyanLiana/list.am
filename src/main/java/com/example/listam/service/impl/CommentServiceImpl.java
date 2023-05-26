package com.example.listam.service.impl;

import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.entity.User;
import com.example.listam.repository.ItemRepository;
import com.example.listam.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ItemRepository itemRepository;

    @Override
    public void save(Comment comment, User currentUser) {
        comment.setCommentDate(new Date());
        comment.setUser(currentUser);
    }

    @Override
    public Optional<Item> findAllByItem_Id(int id) {
        return itemRepository.findById(id);
    }

}
