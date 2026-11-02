package com.petersonsantos9k.picpay_desafio.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record UserDepositRequestDTO(

    @NotNull(message = "User ID is required")
    UUID userId,

    @NotNull(message = "Deposit amount is required")
    @DecimalMin(value = "0.01", message = "Deposit amount must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Deposit amount must have at most 2 decimal places")
    BigDecimal amount

) {}
