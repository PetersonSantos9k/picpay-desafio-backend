package com.petersonsantos9k.picpay_desafio.service;


import com.petersonsantos9k.picpay_desafio.client.payment.PaymentClient;
import com.petersonsantos9k.picpay_desafio.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository repository;
    private final PaymentClient paymentClient;





}
