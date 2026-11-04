package com.petersonsantos9k.picpay_desafio.exception;

import java.time.LocalDateTime;

public record StandardError(
    LocalDateTime timestamp,
    Integer status,
    String error,
    String message
) {
}
