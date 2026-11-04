package com.petersonsantos9k.picpay_desafio.client;

import com.petersonsantos9k.picpay_desafio.dto.client.TransactionAuthorizationResponse;

import java.util.Optional;

public interface Payment {
    Optional<TransactionAuthorizationResponse> authorizeTransaction();
}
