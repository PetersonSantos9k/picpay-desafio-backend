package com.petersonsantos9k.picpay_desafio.service;

import com.petersonsantos9k.picpay_desafio.client.email.EmailClient;
import com.petersonsantos9k.picpay_desafio.exception.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Qualifier("emailClient")
    private final EmailClient emailClient;

    public void sendTransferEmails(){
        try{
            emailClient.sendEmail();
            log.info("Transfer email sent successfully.");
        } catch (ClientException ex){
            log.error("Failed to send sent transfer email: ", ex);
        }

    }
}
