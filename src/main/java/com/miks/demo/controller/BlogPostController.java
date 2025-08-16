package com.miks.demo.controller;

import com.miks.demo.model.BlogPost;
import com.miks.demo.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")  // Allow frontend access
public class BlogPostController {

    @Autowired
    private BlogPostRepository repository;

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return repository.findAll();
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post) {
        return repository.save(post);
    }

    @GetMapping("/{id}")
    public BlogPost getPost(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost updatedPost) {
        return repository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setAuthor(updatedPost.getAuthor());
            post.setImageUrl(updatedPost.getImageUrl());
            return repository.save(post);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
