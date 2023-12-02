package com.alkemy.wallet.service;

import com.alkemy.wallet.entity.Account;

import java.util.List;

public interface IAccountService {

    List<Account> getAccountsByUserId(Long id);

}
