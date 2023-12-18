package com.example.senhas.domain.gerador;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeradorSenhaSegura implements GeradorSenha {

    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final String ESPECIAIS = "@#$%&*-_=+";

    private static final String NUMEROS = "0123456789";

    @Override
    public String geradorSenhaForte(int tamanho, String nome, int tpCaractere, String isNum) {
        if (isNum.equals("S")) {
            return RandomStringUtils.random(tamanho, NUMEROS);
        }

        if (tpCaractere == 1) {
            // Gera uma senha com pelo menos um caractere especial
            String senhaComEspecial = RandomStringUtils.random(1, ESPECIAIS) +
                    RandomStringUtils.random(tamanho - 1, ESPECIAIS + CHARACTERS);
            return embaralharSenha(senhaComEspecial);
        }
        return RandomStringUtils.random(tamanho, CHARACTERS);
    }

    private String embaralharSenha(String senha) {
        // Embaralha a senha para garantir que o caractere especial não seja sempre o primeiro
        List<Character> caracteres = senha.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Collections.shuffle(caracteres);
        return caracteres.stream().map(String::valueOf).collect(Collectors.joining());
    }
}