package com.example.listam.service;

import com.example.listam.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    void deleteById(int id);
}
