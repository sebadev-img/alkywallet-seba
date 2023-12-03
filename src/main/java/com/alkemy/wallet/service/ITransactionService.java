package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.request.DepositRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ITransactionService {

    TransactionDto getTransaction(Long id,@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token);

    Boolean createDeposit(DepositRequestDto depositRequest,String token);
}
