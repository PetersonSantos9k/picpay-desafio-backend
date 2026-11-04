package com.petersonsantos9k.picpay_desafio.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void shouldWithdrawExactBalanceWhenDecimalScaleDiffers() {
        User payer = new User(
            "Payer Test",
            "12345678901",
            "payer@test.com",
            "password123",
            UserType.COMMON
        );

        payer.deposit(new BigDecimal("110.00"));
        payer.withdraw(new BigDecimal("110.0"));

        assertEquals(0, payer.getBalance().compareTo(BigDecimal.ZERO));
    }
}
