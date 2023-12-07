package com.alkemy.wallet.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SimulateFixedTermDepositRequestDto {
    @Positive
    @NotNull
    Double amount;
    @Positive
    @NotNull
    int days;

}
