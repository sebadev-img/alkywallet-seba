package com.alkemy.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Setter
@Getter
public class TransactionDto {
    String userName;
    Long accountId;
    Long transactionId;
    String type;
    Double amount;
    String description;
    Timestamp transactionDate;

}
