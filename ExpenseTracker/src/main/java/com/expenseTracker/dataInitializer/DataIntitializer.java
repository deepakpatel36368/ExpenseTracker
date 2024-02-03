package com.expenseTracker.dataInitializer;

import com.expenseTracker.entity.Category;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.User;
import com.expenseTracker.repository.CategoryRepository;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.repository.UserRepository;
import com.expenseTracker.service.ExpenseService;
import com.expenseTracker.service.UserService;
import com.expenseTracker.serviceImpl.ExpenseServiceImpl;
import com.expenseTracker.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class DataIntitializer {

    private final UserService userService;

    private final ExpenseService expenseService;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DataIntitializer(UserRepository userRepository, ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserService userService, ExpenseService expenseService) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.expenseService = expenseService;
        //LoadUser();
        TestUser();
    }

    private void LoadUser() {
        log.info("Data getting initialise into the database ....");
        List<User> listUser = new ArrayList<>();
        //Populate Users
        for(int i =0; i<10; i++) {
            String userName = "user" + i;
            User user = new User(userName, "password" + i, userName + "@example.com");
            listUser.add(user);
            userRepository.save(user);
        }

        log.info("10 User added into the database");
        List<Category> categoryList = new ArrayList<>();

        for(int i =0 ; i<5; i++) {
            Category category = new Category("category" + i);
            categoryRepository.save(category);
            categoryList.add(category);
        }

        log.info("5 Category added into the database");

        Date date = new Date();
        BigDecimal bigDecimal ;
        int progress = 100;
        for(User user : listUser) {
            for ( Category category : categoryList) {
                bigDecimal = new BigDecimal(progress = progress + 100);
                expenseRepository.save(new Expense(user, bigDecimal, date, "Amount spend on " + category, category ));
            }
        }
        log.info("Expense added for each user into the database");

    }

    public void TestUser() {
//        userService.registerUser(new User(11L,"Ritu" , "deepakpatel81300", "deepak@gmail.com"));
//        for(User user : userService.getAllUsers()) {
//            log.info(user.toString());
//        }
//        userService.loginUser("user0", "password1");
//
//        Expense expense1 = expenseService.getExpenseById(1L);
//
//        log.info("EXPENSE ==== {}", expense1 );
//        List<Expense> list =  expenseService.getExpensesByCategory(expense1.getCategory().getCategoryId());
//
//        for(Expense expense : list) {
//            log.info(expense.toString());
//        }
//
//        //User user = userService.registerUser(new User("Random", "random81300", "randow@gamail.com"));
//        List<Expense> expensesList = expenseService.getAllExpensesByUser(1L);
//
//        BigDecimal sum = BigDecimal.valueOf(0);
//        for(Expense expense : expensesList) {
//            log.info("------------ " + expense.toString());
//            sum = sum.add(sum);
//        }
//        log.info("TOTAL SUM ==============" + sum);
        log.info("------------TESTING STARTED FOR USER ----------");
        List<Expense> expensesList = expenseService.findByUserIdAndCategoryId(1L, 1L);
        for(Expense expense : expensesList) {
            log.info("------------ " + expense.toString());
        }
    }


}
