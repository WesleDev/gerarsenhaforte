package com.example.senhas.config.exceptions;

import org.springframework.http.HttpStatus;

public class MensagemPersonalizada extends RuntimeException {

    private HttpStatus codigo;

    public MensagemPersonalizada(String mensagem) {
        super(mensagem);
    }

    public MensagemPersonalizada(String mensagem, HttpStatus codigo) {
        super(mensagem);
        this.codigo = codigo;
    }

    public HttpStatus getCodigo() {
        return codigo;
    }
}
