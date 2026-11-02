package com.petersonsantos9k.picpay_desafio.exception;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause){
        super(message, cause);
    }

}
