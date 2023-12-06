package com.alkemy.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String user;
    private Long accountId;
    private String currency;
    private Double balance;
    private Double transactionLimit;
}
