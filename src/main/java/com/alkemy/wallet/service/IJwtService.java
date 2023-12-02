package com.alkemy.wallet.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String generateToken(UserDetails user);
}
