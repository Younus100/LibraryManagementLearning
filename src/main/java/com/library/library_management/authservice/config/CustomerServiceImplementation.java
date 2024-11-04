package com.library.library_management.authservice.config;

import com.library.library_management.authservice.exceptions.UserException;
import com.library.library_management.authservice.model.Account;
import com.library.library_management.authservice.repository.AccountRepository;
import jakarta.persistence.DiscriminatorValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImplementation implements UserDetailsService {
    private AccountRepository userRepository;

    public CustomerServiceImplementation(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account user = userRepository.findByUsername(username);
        if(user==null) try {
            throw new UserException("User not foung with " + username);
        } catch (UserException e) {
            throw new RuntimeException(e);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(() -> "ROLE_" + user.getClass().getAnnotation(DiscriminatorValue.class).value());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}