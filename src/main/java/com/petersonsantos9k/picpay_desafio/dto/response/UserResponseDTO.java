package com.petersonsantos9k.picpay_desafio.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponseDTO(
    UUID id,
    String fullName,
    String taxId,
    String email,
    BigDecimal balance
) {
}
