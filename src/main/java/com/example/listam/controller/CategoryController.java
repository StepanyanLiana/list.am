package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String categoriesPage(ModelMap modelMap) {
        List<Category> all = categoryService.findAll();
        modelMap.addAttribute("categories", all);
        return "categories";
    }
    @GetMapping("/categories/add")
    public String addCategoryPage(){
        return "addCategory";
    }

    @PostMapping("/categories/add")
    public String addCategory(@RequestParam("name") String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/remove")
    public String removeCategory(@RequestParam("id") int id){
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
