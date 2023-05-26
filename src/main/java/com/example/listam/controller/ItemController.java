package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CommentRepository;
import com.example.listam.security.CurrentUser;
import com.example.listam.service.CategoryService;
import com.example.listam.service.CommentService;
import com.example.listam.service.HashtagService;
import com.example.listam.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CommentService commentService;
    private final CategoryService categoryService;
    private final HashtagService hashtagService;
    private final CommentRepository commentRepository;

    @GetMapping
    public String itemsPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("items", itemService.findItemsByUser(currentUser.getUser()));
        return "items";
    }

    @GetMapping("/{id}")
    public String singleItemPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Item> byId = itemService.findById(id);
        if (byId.isPresent()) {
            Item item = byId.get();
            List<Comment> comments = commentRepository.findAllByItem_Id(item.getId());
            modelMap.addAttribute("item", item);
            modelMap.addAttribute("comments", comments);
            //modelMap.addAttribute("comments", commentService.findAllByItem_Id(item.getId()));
            return "singleItem";
        } else {
            return "redirect:/items";
        }
    }

    @GetMapping("/add")
    public String itemsAddPage(ModelMap modelMap) {
        List<Category> all = categoryService.findAll();
        modelMap.addAttribute("categories", all);
        modelMap.addAttribute("hashtags", hashtagService.findAll());
        return "addItem";
    }

    @PostMapping("/add")
    public String itemsAdd(@ModelAttribute Item item, @RequestParam("image") MultipartFile multipartFile, @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        itemService.addItem(currentUser.getUser(), multipartFile, item);
        return "redirect:/items";
    }

    @GetMapping("/remove")
    public String removeCategory(@RequestParam("id") int id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }

    @PostMapping("/comment/add")
    public String addComment(@ModelAttribute Comment comment, @AuthenticationPrincipal CurrentUser currentUser) {
        comment.setCommentDate(new Date());
        comment.setUser(currentUser.getUser());
        commentRepository.save(comment);
        return "redirect:/items/" + comment.getItem().getId();
    }
}
