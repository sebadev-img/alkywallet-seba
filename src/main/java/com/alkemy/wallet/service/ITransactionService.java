package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.request.TransactionRequestDto;
import com.alkemy.wallet.dto.response.TransactionResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ITransactionService {

    TransactionResponseDto getTransaction(Long id,String token);

    Boolean createDeposit(TransactionRequestDto depositRequest, String token);
    TransactionResponseDto createPayment(TransactionRequestDto paymentRequest, String token);
}
