package com.alkemy.wallet.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequestDto {

    private String firstName;

    private String lastName;

    private String password;
}
