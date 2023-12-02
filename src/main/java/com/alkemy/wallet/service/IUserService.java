package com.alkemy.wallet.service;

import com.alkemy.wallet.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User deleteUserById(Long id);

    UserDetailsService userDetailsService();
}
