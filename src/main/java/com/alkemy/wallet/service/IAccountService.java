package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.enums.ECurrency;

import java.util.List;

public interface IAccountService {

    List<AccountDto> getAccountsByUserId(Long userId);

    AccountDto createAccount(String currency, String token);

}
