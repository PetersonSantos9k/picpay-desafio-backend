package com.petersonsantos9k.picpay_desafio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDTO(

    @NotBlank(message = "Full name is required")
    @Size(
        min = 3,
        max = 100
    )
    String fullName,

    @NotBlank(message = "Tax ID is required")
    @Pattern(
        regexp = "\\d{11}|\\d{14}",
        message = "Tax ID must contain 11 digits (CPF) or 14 digits (CNPJ)"
    )
    String taxId,

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    String email,

    @NotBlank(message = "Password is required")
    @Size(
        min = 8,
        max = 64,
        message = "Password must be between 8 and 64 characters"
    )
    String password
) {
}
