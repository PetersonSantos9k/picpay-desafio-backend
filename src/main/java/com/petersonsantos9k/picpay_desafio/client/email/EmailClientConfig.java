package com.petersonsantos9k.picpay_desafio.client.email;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
@EnableConfigurationProperties(EmailClientProperties.class)
public class EmailClientConfig {
    @Bean(name = "emailRestClient")
    @Primary
    RestClient emailRestClient(RestClient.Builder builder,
                                 EmailClientProperties properties) {

        Duration connectTimeout = properties.getConnectTimeout();
        Duration readTimeout = properties.getReadTimeout();

        var httpClient = HttpClient.newBuilder()
            .connectTimeout(connectTimeout)
            .build();
        var requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(readTimeout);
        builder.baseUrl(properties.getBaseUrl());
        return builder.build();
    }


}
