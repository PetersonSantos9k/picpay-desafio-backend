package com.petersonsantos9k.picpay_desafio.dto.client;

public record TransactionAuthorizationResponse(
    String status,
    Data data
) {
    public record Data(
        boolean authorization
    ) {}
}
