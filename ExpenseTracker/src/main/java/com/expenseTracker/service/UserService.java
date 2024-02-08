package com.expenseTracker.service;

import com.expenseTracker.entity.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    User loginUser(String username, String password);

    User getUserById(Long userId);

    List<User> getAllUsers();
}
