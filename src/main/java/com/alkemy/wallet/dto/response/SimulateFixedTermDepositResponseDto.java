package com.alkemy.wallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class SimulateFixedTermDepositResponseDto {
    Double investedAmount;
    Timestamp creationDate;
    Timestamp closingDate;
    Double  totalInterest;
    Double totalValue;

}
