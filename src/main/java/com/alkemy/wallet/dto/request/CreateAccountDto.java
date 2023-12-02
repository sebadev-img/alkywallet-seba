package com.alkemy.wallet.dto.request;

import com.alkemy.wallet.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountDto {
    private Long userId;
    private ECurrency currency;
}
