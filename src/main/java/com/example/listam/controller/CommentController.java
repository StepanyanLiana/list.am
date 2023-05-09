package com.example.listam.controller;

import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class CommentController {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments")
    public String commentsPage(ModelMap modelMap) {
        List<Comment> all = commentRepository.findAll();
        modelMap.addAttribute("comments", all);
        return "comments";
    }

    @GetMapping("/comments/add")
    public String addCommentPage(ModelMap modelMap){
        List<Item> all = itemRepository.findAll();
        modelMap.addAttribute("items", all);
        return "singleItem";
    }

    @PostMapping("/comments/add")
    public String addComment(@RequestParam("comment") String comment1) {
        Comment comment = new Comment();
        comment.setComment(comment1);
        commentRepository.save(comment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/remove")
    public String removeComment(@RequestParam("id") int id){
        commentRepository.deleteById(id);
        return "redirect:/comments";
    }
}
