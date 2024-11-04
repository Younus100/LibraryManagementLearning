package com.library.library_management.authservice.service;

import com.library.library_management.authservice.model.Account;
import com.library.library_management.authservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findUserByEmail(String email) {
        return null;
    }
}
