package com.petersonsantos9k.picpay_desafio.client;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class Properties {

    private Boolean enabled;
    private String baseUrl;
    private Duration connectTimeout;
    private Duration readTimeout;
    private Integer maxRetries;

}
