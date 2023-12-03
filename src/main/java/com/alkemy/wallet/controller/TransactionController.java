package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.request.DepositRequestDto;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.TransactionServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Boolean> createDeposit(@RequestBody DepositRequestDto depositRequest, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        Boolean response = transactionService.createDeposit(depositRequest,token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
