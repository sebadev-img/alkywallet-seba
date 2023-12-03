package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.request.DepositRequestDto;

public interface ITransactionService {

    Boolean createDeposit(DepositRequestDto depositRequest,String token);
}
