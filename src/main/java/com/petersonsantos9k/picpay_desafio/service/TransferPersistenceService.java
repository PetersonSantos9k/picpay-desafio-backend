package com.petersonsantos9k.picpay_desafio.service;

import com.petersonsantos9k.picpay_desafio.domain.Transfer;
import com.petersonsantos9k.picpay_desafio.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferPersistenceService {

    private final TransferRepository repository;

    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public void save(Transfer transfer) {
        repository.save(transfer);
    }


}
