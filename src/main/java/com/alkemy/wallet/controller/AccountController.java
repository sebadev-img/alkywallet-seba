package com.alkemy.wallet.controller;

import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.service.AccountServiceImpl;
import com.alkemy.wallet.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    private final IAccountService accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable Long id) {
        List<Account> accountsDto = accountService.getAccountsByUserId(id);
        return new ResponseEntity<>(accountsDto, HttpStatus.OK);
    }

}
