package com.alkemy.wallet.service;

import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.enums.ECurrency;

import java.util.List;

public interface IAccountService {

    List<Account> getAccountsByUserId(Long id);

    Account createAccount(Long userId, ECurrency currency);

}
