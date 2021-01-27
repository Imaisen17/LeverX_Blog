package com.leverx.Blog.controllers;

import com.leverx.Blog.models.Posts;
import com.leverx.Blog.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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
    @GetMapping("/blog/add")
    public String addBlog(Model model){
        return "blog_add";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Posts post = new Posts(title, anons, full_text);
        postsRepository.save(post);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String blogPostDetails(@PathVariable(value = "id") long id, Model model){
        if (!postsRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Posts> post = postsRepository.findById(id);
        ArrayList<Posts> result = new ArrayList<>();
        post.ifPresent(result ::add);
        model.addAttribute("post", result);
        return "blog_details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogPostEdit(@PathVariable(value = "id") long id, Model model){
        if (!postsRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Posts> post = postsRepository.findById(id);
        ArrayList<Posts> result = new ArrayList<>();
        post.ifPresent(result ::add);
        model.addAttribute("post", result);
        return "blog_edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Posts post = postsRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postsRepository.save(post);
        return "redirect:/blog";

    }
    @PostMapping("/blog/{id}/delete")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model){
        Posts post = postsRepository.findById(id).orElseThrow();
        postsRepository.delete(post);
        return "redirect:/blog";

    }

}
