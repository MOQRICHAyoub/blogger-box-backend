package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "API for managing categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Category createCategory(@RequestParam String name) {
        return service.create(name);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable UUID id, @RequestParam String name) {
        return service.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}
