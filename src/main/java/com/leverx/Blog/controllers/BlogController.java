package com.leverx.Blog.controllers;

import com.leverx.Blog.models.Posts;
import com.leverx.Blog.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @Autowired
    PostsRepository postsRepository;
    @GetMapping("/blog")
    public String blogPage(Model model){
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog_page";
    }
}
