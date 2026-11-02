package com.petersonsantos9k.picpay_desafio.client.payment;

import com.petersonsantos9k.picpay_desafio.dto.client.TransactionAuthorizationResponse;
import com.petersonsantos9k.picpay_desafio.exception.ClientException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component("paymentClient")
public class PaymentClient {

    private final RestClient restClient;
    private final PaymentClientProperties properties;
    public PaymentClient(
        @Qualifier("paymentRestClient") RestClient restClient,
        PaymentClientProperties properties
    ) {
        this.restClient = restClient;
        this.properties = properties;
    }

    public Optional<TransactionAuthorizationResponse> authorizeTransaction(){
        try{
            return Optional.ofNullable(
                restClient.get()
                    .uri(properties.getAuthorizeTransactionPath())
                    .retrieve()
                    .body(TransactionAuthorizationResponse.class)
            );
        } catch (HttpClientErrorException e) {
            throw new ClientException("Error occurred while authorizing transaction: " + e.getMessage(), e);
        }
    }


}
