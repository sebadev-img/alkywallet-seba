package com.alkemy.wallet.controller;

import com.alkemy.wallet.dto.request.LoanRequestDto;
import com.alkemy.wallet.dto.response.LoanResponseDto;
import com.alkemy.wallet.service.ILoanService;
import com.alkemy.wallet.service.LoanService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    private final ILoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<LoanResponseDto> simulateLoan(@RequestBody LoanRequestDto loanRequest, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        LoanResponseDto loanResponse = loanService.calculateLoan(loanRequest,token);
        return new ResponseEntity<>(loanResponse, HttpStatus.OK);
    }
}
