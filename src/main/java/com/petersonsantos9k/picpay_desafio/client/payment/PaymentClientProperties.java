package com.petersonsantos9k.picpay_desafio.client.payment;

import com.petersonsantos9k.picpay_desafio.client.Properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "integration.payment")
@Getter
@Setter
public class PaymentClientProperties extends Properties {
    private String authorizeTransactionPath;
}
