package com.expenseTracker.service;

import com.expenseTracker.entity.Category;
import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long categoryId);

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategories();
}
