package com.alkemy.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    private Long id;
    private String currency;
    private Double balance;
    private Double transactionLimit;
    private List<?> transactions;
    private List<?> fixedTermDeposits;
}
