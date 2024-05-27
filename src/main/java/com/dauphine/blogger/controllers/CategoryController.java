package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all categories", description = "Retrieve all categories or filter like name")
    public List<Category> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? service.getAll()
                : service.getAllLikeName(name);
        return categories;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id", description = "Retrieve a category by its ID")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create new category", description = "Create a new category")
    public Category createCategory(@RequestParam String name) {
        return service.create(name);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update category name", description = "Update the name of an existing category")
    public Category updateCategory(@PathVariable UUID id, @RequestParam String name) {
        return service.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete a category by its ID")
    public boolean deleteCategory(@PathVariable UUID id) {
        return service.deleteById(id);
    }
}
