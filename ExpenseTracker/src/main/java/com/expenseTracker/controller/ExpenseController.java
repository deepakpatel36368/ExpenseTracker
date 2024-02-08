package com.expenseTracker.controller;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense addedExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(expense, HttpStatus.CREATED);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense, @PathVariable Long expenseId){
        expense.setExpenseId(expenseId);
        Expense updatedExpense = expenseService.updateExpense(expense);
        return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long expenseId){
        expenseService.deleteExpense(expenseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long expenseId) {
       Expense expense = expenseService.getExpenseById(expenseId);
       return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpense();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable Long categoryId) {
        List<Expense> expenses = expenseService.getExpensesByCategory(categoryId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expense/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId){
        List<Expense> expenses = expenseService.getAllExpensesByUser(userId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/expense/userByCategory/{userId}")
    public ResponseEntity<List<Expense>> findByUserIdAndCategoryId(@PathVariable Long userId, @RequestParam Long categoryId) {
        List<Expense> expenses = expenseService.findByUserIdAndCategoryId(userId, categoryId);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

}
