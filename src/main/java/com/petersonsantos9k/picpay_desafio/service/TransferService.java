package com.petersonsantos9k.picpay_desafio.service;

import com.petersonsantos9k.picpay_desafio.client.Payment;
import com.petersonsantos9k.picpay_desafio.domain.Transfer;
import com.petersonsantos9k.picpay_desafio.domain.User;
import com.petersonsantos9k.picpay_desafio.dto.client.TransactionAuthorizationResponse;
import com.petersonsantos9k.picpay_desafio.dto.request.TransferRequestDTO;
import com.petersonsantos9k.picpay_desafio.exception.TransferException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferService {

    private final TransferPersistenceService persistenceService;

    @Qualifier("paymentClient")
    private final Payment paymentClient;
    private final UserService userService;
    private final EmailService emailService;


    @Transactional
    public void transfer(TransferRequestDTO transfer){

        BigDecimal value = transfer.value();


        User payer = userService.findUserById(transfer.payer());
        User payee = userService.findUserById(transfer.payee());

        validateTransaction(transfer, payer);
        if(!authorizeTransaction()){
            throw new TransferException("Transaction authorization failed");
        }

        payer.withdraw(value);
        payee.deposit(value);
        userService.updateUser(payer);
        userService.updateUser(payee);


        emailService.sendTransferEmails();

        Transfer newTransfer = new Transfer(payer, payee, value);

        try{
            persistenceService.save(newTransfer);
        } catch (DataAccessException ex){
            log.error("Error occurred while saving transfer", ex);
        }

    }

    private boolean authorizeTransaction() {

        TransactionAuthorizationResponse response = paymentClient.authorizeTransaction()
            .orElseThrow(() -> new TransferException("Failed to authorize transaction"));

        if(!response.status().equalsIgnoreCase("success")) {
            throw new TransferException("Transaction authorization failed with status: " + response.status());
        }
        return response.data().authorization();
    }

    private void validateTransaction(TransferRequestDTO transfer, User payer) {

        if(transfer.value() == null || transfer.value().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransferException("Transfer value must be greater than zero");
        }

        if(transfer.payer() == null || transfer.payee() == null) {
            throw new TransferException("Payer and payee must be specified");
        }

        if(transfer.payee().equals(transfer.payer())) {
            throw new TransferException("Payer and payee cannot be the same user");
        }

        if(payer.getBalance().compareTo(transfer.value()) < 0) {
            throw new TransferException("Insufficient balance for the transfer");
        }


    }

}
