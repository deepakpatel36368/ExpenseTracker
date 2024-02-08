package com.expenseTracker.service;

import com.expenseTracker.entity.Expense;
import java.util.List;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    Expense updateExpense(Expense expense);

    void deleteExpense(Long expenseId);

    Expense getExpenseById(Long expenseId);

    List<Expense> getAllExpensesByUser(Long userId);

    List<Expense> getExpensesByCategory(Long categoryId);

    List<Expense> getAllExpense();

    List<Expense> findByUserIdAndCategoryId(Long userId, Long categoryId);

    // Add other expense-related methods as needed
}
