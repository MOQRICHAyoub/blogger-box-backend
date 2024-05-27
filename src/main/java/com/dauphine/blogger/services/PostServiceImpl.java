package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> temporaryPosts;

    public PostServiceImpl() {
        this.temporaryPosts = new ArrayList<>();
        // Ajouter quelques posts temporaires pour le test
        temporaryPosts.add(new Post(UUID.randomUUID(), "First Post", "This is the first post content", UUID.randomUUID()));
        temporaryPosts.add(new Post(UUID.randomUUID(), "Second Post", "This is the second post content", UUID.randomUUID()));
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return temporaryPosts.stream()
                .filter(post -> post.getCategory().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAll() {
        return temporaryPosts;
    }

    @Override
    public Post getById(UUID id) {
        return temporaryPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post(UUID.randomUUID(), title, content, categoryId);
        temporaryPosts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = temporaryPosts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryPosts.removeIf(post -> post.getId().equals(id));
    }
}
