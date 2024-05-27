package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category();
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = getById(id);
        if (category != null) {
            category.setName(name);
            return repository.save(category);
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> getAllLikeName(String name) {
        return repository.findAllLikeName(name);
    }
}
