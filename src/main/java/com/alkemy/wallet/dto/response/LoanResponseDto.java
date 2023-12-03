package com.alkemy.wallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoanResponseDto {
    Double amount;
    int months;
    String interestRate;
    Double paymentPerMonth;
    Double totalInterest;
    Double totalPayment;

}
