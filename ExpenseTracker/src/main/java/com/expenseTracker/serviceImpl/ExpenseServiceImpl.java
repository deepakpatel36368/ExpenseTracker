package com.expenseTracker.serviceImpl;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        Optional<Expense> optional = expenseRepository.findById(expense.getExpenseId());
        if(optional.isPresent()) {
            return expenseRepository.save(expense);
        } else {
            throw  new RuntimeException("User not found");
        }
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Optional<Expense> optional = expenseRepository.findById(expenseId);
        if(optional.isPresent()) {
            expenseRepository.deleteById(expenseId);
        } else {
            throw  new RuntimeException("User not found");
        }
    }

    @Override
    public Expense getExpenseById(Long expenseId) {
        Optional<Expense> optional = expenseRepository.findById(expenseId);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            throw  new RuntimeException("User not found");
        }
    }

    @Override
    public List<Expense> getAllExpensesByUser(Long userId) {
        return expenseRepository.findAllByUserUserId(userId);
    }

    @Override
    public List<Expense> getExpensesByCategory(Long categoryId) {
        return expenseRepository.findAllByCategoryCategoryId(categoryId);
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> findByUserIdAndCategoryId(Long userId, Long categoryId) {
        return expenseRepository.findByUserIdAndCategoryId(userId, categoryId);
    }
}
