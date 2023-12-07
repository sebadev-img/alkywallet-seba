package com.alkemy.wallet.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoanRequestDto {
    @Positive
    @NotNull
    Double amount;
    @Positive
    @NotNull
    Integer months;
}
