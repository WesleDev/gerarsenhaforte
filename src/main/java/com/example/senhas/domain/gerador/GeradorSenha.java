package com.example.senhas.domain.gerador;

public interface GeradorSenha {
    String geradorSenhaForte(int tamanho, String nome, int tpCaractere, String isNum);
}
