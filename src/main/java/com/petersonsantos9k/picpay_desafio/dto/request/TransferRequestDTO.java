package com.petersonsantos9k.picpay_desafio.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequestDTO(

    @NotNull(message = "Transfer amount is required")
    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Transfer amount must have at most 2 decimal places")
    BigDecimal value,

    @NotNull(message = "Payer ID is required")
    UUID payer,

    @NotNull(message = "Payee ID is required")
    UUID payee

) {}
