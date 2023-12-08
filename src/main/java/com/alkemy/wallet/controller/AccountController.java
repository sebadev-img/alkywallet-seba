package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.AccountDto;


import com.alkemy.wallet.dto.request.UpdateAccountRequestDto;
import com.alkemy.wallet.dto.response.PageableAccountResponseDto;
import com.alkemy.wallet.service.AccountServiceImpl;
import com.alkemy.wallet.service.IAccountService;
import jakarta.validation.Valid;
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

    @GetMapping
    public ResponseEntity<?> getAllAccounts(@RequestParam(defaultValue = "0") int page){
        PageableAccountResponseDto response = accountService.getAllAccounts(page);
        return new ResponseEntity<>(response,HttpStatus.OK);
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

    @PatchMapping("/{id}")
    public ResponseEntity<AccountDto> updateTransactionLimit(@PathVariable Long id, @Valid @RequestBody UpdateAccountRequestDto updateRequest, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        System.out.println(updateRequest.getNewTransactionLimit());
        AccountDto accountDto = accountService.updateTransactionLimit(id,updateRequest,token);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

}
