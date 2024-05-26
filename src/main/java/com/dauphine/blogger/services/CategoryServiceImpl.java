package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> temporaryCategories;

    public CategoryServiceImpl() {
        this.temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @Override
    public List<Category> getAll() {
        return temporaryCategories;
    }

    @Override
    public Category getById(UUID id) {
        return temporaryCategories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(UUID.randomUUID(), name);
        temporaryCategories.add(category);
        return category;
    }

    @Override
    public Category updateName(UUID id, String name) {
        Category category = temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (category != null) {
            category.setName(name);
        }
        return category;
    }

    @Override
    public boolean deleteById(UUID id) {
        return temporaryCategories.removeIf(category -> category.getId().equals(id));
    }
}
