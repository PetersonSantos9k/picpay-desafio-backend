package com.petersonsantos9k.picpay_desafio.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRequestDTO(
    BigDecimal value,
    UUID payer,
    UUID payee
) {
}
