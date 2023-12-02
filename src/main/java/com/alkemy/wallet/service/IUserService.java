package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {
    List<UserDto> getUsers();
    User deleteUserById(Long id);

    UserDetailsService userDetailsService();
}
