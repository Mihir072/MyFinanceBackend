package com.mihir.MyFinace.services;

import com.mihir.MyFinace.models.Category;
import com.mihir.MyFinace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category updateCategory(String id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }
    public String deleteCategory(String id) {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }
}
