package com.alkemy.wallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
