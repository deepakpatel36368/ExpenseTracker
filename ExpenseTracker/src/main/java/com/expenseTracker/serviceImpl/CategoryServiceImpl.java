package com.expenseTracker.serviceImpl;

import com.expenseTracker.entity.Category;
import com.expenseTracker.repository.CategoryRepository;
import com.expenseTracker.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        getCategoryById(category.getCategoryId());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        getCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            return categoryRepository.getById(categoryId);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
