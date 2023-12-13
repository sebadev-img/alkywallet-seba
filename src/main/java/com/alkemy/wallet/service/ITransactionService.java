package com.alkemy.wallet.service;


import com.alkemy.wallet.dto.request.SendTransactionRequestDto;
import com.alkemy.wallet.dto.request.TransactionRequestDto;
import com.alkemy.wallet.dto.response.SendTransactionResponseDto;
import com.alkemy.wallet.dto.response.TransactionResponseDto;

public interface ITransactionService {
    TransactionResponseDto getTransaction(Long id,String token);
    Boolean createDeposit(TransactionRequestDto depositRequest, String token);
    TransactionResponseDto createPayment(TransactionRequestDto paymentRequest, String token);
    SendTransactionResponseDto sendArs(SendTransactionRequestDto transactionRequest, String token);
    SendTransactionResponseDto sendUsd(SendTransactionRequestDto transactionRequest, String token);
}
