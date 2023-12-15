package com.example.senhas.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseErro {
    private Integer codigo;
    private String mensagem;
}