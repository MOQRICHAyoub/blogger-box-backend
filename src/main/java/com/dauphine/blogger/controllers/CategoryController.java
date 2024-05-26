package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.dto.UpdateCategoryRequest;
import com.dauphine.blogger.models.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API", description = "Operations related to categories")
public class CategoryController {

    private final List<Category> temporaryCategories;

    public CategoryController() {
        temporaryCategories = new ArrayList<>();
        temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Returns a list of all categories")
    public List<Category> retrieveAllCategories() {
        return temporaryCategories;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Returns a category by its ID")
    public Category getCategoryById(@PathVariable UUID id) {
        return temporaryCategories.stream().filter(category -> category.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new category", description = "Creates a new category")
    public Category createCategory(@RequestBody CreationCategoryRequest request) {
        Category category = new Category(UUID.randomUUID(), request.getName());
        temporaryCategories.add(category);
        return category;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category", description = "Updates an existing category by ID")
    public Category updateCategory(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {
        Category existingCategory = temporaryCategories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
        if (existingCategory != null) {
            existingCategory.setName(request.getName());
        }
        return existingCategory;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Deletes a category by ID")
    public void deleteCategory(@PathVariable UUID id) {
        temporaryCategories.removeIf(category -> category.getId().equals(id));
    }
}
