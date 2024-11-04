package com.library.library_management.authservice.config;

import com.library.library_management.authservice.model.Account;
import com.library.library_management.authservice.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

    public static Account getCurrentUser(AccountService accountService) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String email = authentication.getName();
            return accountService.findUserByEmail(email);
        } else {
            throw  new Exception("User Not Found Exception"); // or throw an exception, depending on your requirements
        }
    }
}