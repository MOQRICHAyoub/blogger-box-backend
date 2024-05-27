package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(new Category(categoryId, ""));
        post.setCreatedDate(LocalDateTime.now());
        return repository.save(post);
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            return repository.save(post);
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> findAllByTitleOrContent(String value) {
        return repository.findAllByTitleOrContent(value);
    }
}
