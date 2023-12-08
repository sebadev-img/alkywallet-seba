package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.dto.response.PageableUserResponseDto;
import com.alkemy.wallet.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {
    PageableUserResponseDto getUsers(int page);
    User deleteUserById(Long id);

    UserDetailsService userDetailsService();
}
