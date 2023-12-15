package com.example.senhas.domain.dto;

import lombok.Data;

@Data
public class HistoricoSenhaDTO {

    private Long id;
    private String nome;
    private String senha;
    private String data;
}
