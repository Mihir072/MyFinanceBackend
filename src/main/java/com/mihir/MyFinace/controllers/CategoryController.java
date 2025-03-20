package com.mihir.MyFinace.controllers;

import com.mihir.MyFinace.models.Category;
import com.mihir.MyFinace.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable String id) {
        return categoryService.deleteCategory(id);
    }
}

