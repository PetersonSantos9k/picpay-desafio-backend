package com.petersonsantos9k.picpay_desafio.controller;


import com.petersonsantos9k.picpay_desafio.dto.request.TransferRequestDTO;
import com.petersonsantos9k.picpay_desafio.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody @Valid TransferRequestDTO transferRequestDTO){
        transferService.transfer(transferRequestDTO);
        return ResponseEntity.ok().build();
    }


}
