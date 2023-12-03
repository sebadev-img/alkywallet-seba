package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDto;


import com.alkemy.wallet.service.AccountServiceImpl;
import com.alkemy.wallet.service.IAccountService;
import org.springframework.http.HttpHeaders;
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

    @PostMapping()
    public ResponseEntity<AccountDto> createAccount(@RequestParam String currency, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        //TODO: crear dto de respuesta
       AccountDto account = accountService.createAccount(currency,token);
       return new ResponseEntity<>(account,HttpStatus.CREATED);
    }

}
