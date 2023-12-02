package com.alkemy.wallet.service;

import com.alkemy.wallet.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User deleteUserById(Long id);
}
