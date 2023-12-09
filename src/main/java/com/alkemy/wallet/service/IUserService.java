package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.dto.request.UserUpdateRequestDto;
import com.alkemy.wallet.dto.response.PageableUserResponseDto;
import com.alkemy.wallet.dto.response.UserInfoResponseDto;
import com.alkemy.wallet.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService {
    PageableUserResponseDto getUsers(int page);
    UserInfoResponseDto getUserById(Long id,String token);
    User deleteUserById(Long id);
    UserInfoResponseDto updateUser(Long id, UserUpdateRequestDto userRequest, String token);

    UserDetailsService userDetailsService();
}
