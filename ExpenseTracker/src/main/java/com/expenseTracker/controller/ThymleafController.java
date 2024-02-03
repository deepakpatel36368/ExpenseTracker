package com.expenseTracker.controller;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/expense")
public class ThymleafController {

    public final UserController userController;

    public final ExpenseController expenseController;

    @Autowired
    public ThymleafController(UserController userController, ExpenseController expenseController) {
        this.userController = userController;
        this.expenseController = expenseController;
    }

    @GetMapping("/home")
    public String showHomePage() {
        // This method will return the name of the HTML template for the homepage
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        // Add an empty User object to the model for the login form
        model.addAttribute("user", new User());
        // Return the name of the HTML template for the login page
        return "loginPage";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        // Add an empty User object to the model for the login form
        model.addAttribute("user", new User());
        // Return the name of the HTML template for the login page
        return "registrationPage";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        ResponseEntity<User> responseEntity = userController.registerUser(user);
        if(responseEntity.getStatusCode() == HttpStatus.CREATED) {
            User registeredUser = responseEntity.getBody();
            model.addAttribute("user", new User());
            model.addAttribute("errorMessage", "User Create successfully with userName " + registeredUser.getUsername() + ". Please login !!");
            return "loginPage";
        } else {
            model.addAttribute("user", new User());
            model.addAttribute("errorMessage", "Something went wrong please try again !!");
            return "registrationPage";
        }
    }

    @PostMapping("/auth")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {

        ResponseEntity<User> responseEntity = userController.loginUser(username, password);
        user = responseEntity.getBody();
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            User user = responseEntity.getBody();
            List<Expense> expenseList = expenseController.getExpensesByUserId(user.getUserId()).getBody();
            model.addAttribute("user", user);
            model.addAttribute("expenseList", expenseList);
            // If login is successful, redirect to a page to display user details
             return "ExpenseDashboardPage";
        } else {
            // If login fails, add an error message to the model and return to the login page
            model.addAttribute("errorMessage", "Invalid username or password");
            model.addAttribute("user", new User());
            return "loginPage";
        }
    }

//    @PostMapping("/delete/{expenseId}")
//    public void deleteExpense(@PathVariable Long expenseId){
//        ResponseEntity<Expense> responseEntity = expenseController.deleteExpense(expenseId);
//        if(responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
//            loginUser(user.getUsername(), user.getPassword(), null);
//        } else {
//            loginUser(user.getUsername(), user.getPassword(), null);
//
//        }
//
//    }

    public User user = null;


}
