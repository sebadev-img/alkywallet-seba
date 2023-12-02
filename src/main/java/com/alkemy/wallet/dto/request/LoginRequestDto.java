package com.alkemy.wallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
