package com.petersonsantos9k.picpay_desafio.client.payment;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "integration.payment")
@Getter
@Setter
public class PaymentClientProperties {
    private Boolean enabled;
    private String baseUrl;
    private String authorizeTransactionPath;
    private Duration connectTimeout;
    private Duration readTimeout;
    private Integer maxRetries;
}
