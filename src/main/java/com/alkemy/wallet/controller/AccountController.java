package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.dto.request.CreateAccountDto;
import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.service.AccountServiceImpl;
import com.alkemy.wallet.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {


    private final IAccountService accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AccountDto>> getAccountsByUserId(@PathVariable Long id) {
        List<AccountDto> accountsDto = accountService.getAccountsByUserId(id);
        return new ResponseEntity<>(accountsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountDto body){
        //TODO: crear dto de respuesta
       Account account = accountService.createAccount(body.getUserId(),body.getCurrency());
       return new ResponseEntity<>(account,HttpStatus.CREATED);
    }

}
