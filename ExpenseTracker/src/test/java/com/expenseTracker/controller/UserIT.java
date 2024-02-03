package com.expenseTracker.controller;

import com.expenseTracker.entity.LoginUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {
    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testLogin() throws Exception {
        String username = "admin";
        String password = "admin";

        LoginUser loginUser = new LoginUser("admin", "admin");

        String jsonUser = objectMapper.writeValueAsString(loginUser);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
    }


}
