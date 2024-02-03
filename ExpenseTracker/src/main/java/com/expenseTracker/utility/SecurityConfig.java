package com.expenseTracker.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable() // Disable CSRF protection
                .authorizeRequests()
                .anyRequest().permitAll(); // Allow access to all requests

//        http
//                .authorizeRequests()
//                     .antMatchers("/**").permitAll()
////                    .antMatchers("/api/user/**").permitAll()
////                    .antMatchers("/api/expense/**").permitAll()
////                    .antMatchers("/api/category/**").permitAll()
////                    .antMatchers("/api/user/admin/**").hasRole("ADMIN")
////                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .logoutUrl("/logout")
//                    .permitAll();

    }
}

