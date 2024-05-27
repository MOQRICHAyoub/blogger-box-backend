package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "API for managing posts")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all posts", description = "Get a list of all posts")
    public List<Post> getAllPosts() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a post by ID", description = "Get the details of a specific post by its ID")
    public Post retrievePostById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Retrieve posts by category ID", description = "Get all posts for a specific category")
    public List<Post> getAllPostsByCategoryId(@PathVariable UUID categoryId) {
        return service.getAllByCategoryId(categoryId);
    }

    @GetMapping("/search")
    @Operation(summary = "Find posts by title or content", description = "Retrieve posts that match the title or content")
    public List<Post> findAllByTitleOrContent(@RequestParam String value) {
        return service.findAllByTitleOrContent(value);
    }

    @PostMapping
    @Operation(summary = "Create a new post", description = "Create a new post with a given title, content, and category ID")
    public Post createPost(@RequestParam String title, @RequestParam String content, @RequestParam UUID categoryId) {
        return service.create(title, content, categoryId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post", description = "Update the title and content of an existing post by its ID")
    public Post updatePost(@PathVariable UUID id, @RequestParam String title, @RequestParam String content) {
        return service.update(id, title, content);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post", description = "Delete a post by its ID")
    public boolean deletePost(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}
