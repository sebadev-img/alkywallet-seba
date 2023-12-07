package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.request.SimulateFixedTermDepositRequestDto;
import com.alkemy.wallet.dto.response.SimulateFixedTermDepositResponseDto;
import com.alkemy.wallet.service.FixedTermDepositServiceImpl;
import com.alkemy.wallet.service.IFixedTermDepositService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fixedTerm")
public class FixedTermDepositController {

    private final IFixedTermDepositService fixedTermService;

    public FixedTermDepositController(FixedTermDepositServiceImpl fixedTermService) {
        this.fixedTermService = fixedTermService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<SimulateFixedTermDepositResponseDto> simulateFixedTermDeposit(@Valid @RequestBody SimulateFixedTermDepositRequestDto fixedTermRequest, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token){
        SimulateFixedTermDepositResponseDto fixedTermResponse = fixedTermService.simulateFixedTermDeposit(fixedTermRequest,token);
        return new ResponseEntity<>(fixedTermResponse, HttpStatus.OK);
    }
}
