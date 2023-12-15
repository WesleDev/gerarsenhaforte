package com.example.senhas.domain.gerador;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class GeradorSenhaSegura implements GeradorSenha {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*-_=+";

    @Override
    public String geradorSenhaForte(int tamanho, String nome) {
        return RandomStringUtils.random(tamanho, CHARACTERS);
    }
}
