package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "Operations related to posts")
public class PostController {

    private final List<Post> temporaryPosts;

    public PostController() {
        temporaryPosts = new ArrayList<>();
        temporaryPosts.add(new Post(UUID.randomUUID(), "First post", "Content of the first post"));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second post", "Content of the second post"));
    }

    @GetMapping
    @Operation(summary = "Get all posts", description = "Returns a list of all posts")
    public List<Post> retrieveAllPosts() {
        return temporaryPosts;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get post by ID", description = "Returns a post by its ID")
    public Post getPostById(@PathVariable UUID id) {
        return temporaryPosts.stream().filter(post -> post.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new post", description = "Creates a new post")
    public Post createPost(@RequestBody CreationPostRequest request) {
        Post post = new Post(UUID.randomUUID(), request.getTitle(), request.getContent());
        temporaryPosts.add(post);
        return post;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing post", description = "Updates an existing post by ID")
    public Post updatePost(@PathVariable UUID id, @RequestBody UpdatePostRequest request) {
        Post existingPost = temporaryPosts.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(request.getTitle());
            existingPost.setContent(request.getContent());
        }
        return existingPost;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a post", description = "Deletes a post by ID")
    public void deletePost(@PathVariable UUID id) {
        temporaryPosts.removeIf(post -> post.getId().equals(id));
    }
}
