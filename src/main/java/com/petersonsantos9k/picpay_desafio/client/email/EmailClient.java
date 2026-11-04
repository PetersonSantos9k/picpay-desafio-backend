package com.petersonsantos9k.picpay_desafio.client.email;

import com.petersonsantos9k.picpay_desafio.client.Email;
import com.petersonsantos9k.picpay_desafio.client.payment.PaymentClientProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;


@Component("emailClient")
public class EmailClient implements Email {

    private final RestClient restClient;
    private final EmailClientProperties properties;
    public EmailClient(
        @Qualifier("emailRestClient") RestClient restClient,
        EmailClientProperties properties
    ) {
        this.restClient = restClient;
        this.properties = properties;
    }


    @Override
    public void sendEmail() {
        int maxRetries = properties.getMaxRetries() != null ? Math.max(0, properties.getMaxRetries()) : 0;
       RestClientException lastException = null;

       for(int attempt = 0; attempt <= maxRetries; attempt++) {
            try{
                restClient.post()
                    .uri(properties.getSendEmailPath())
                    .retrieve()
                    .toEntity(String.class);
                return;
            } catch (RestClientException e) {
                lastException = e;
            }
       }

         throw new RuntimeException(
                "Error occurred while sending email after " + (maxRetries + 1) + " attempt(s): "
                 + lastException.getMessage(),
                lastException
          );
    }
}
