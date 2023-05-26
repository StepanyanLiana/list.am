package com.example.listam.service.impl;

import com.example.listam.entity.Hashtag;
import com.example.listam.repository.HashtagRepository;
import com.example.listam.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {
    private final HashtagRepository hashtagRepository;
    @Override
    public List<Hashtag> findAll() {
        return hashtagRepository.findAll();
    }
}
