package com.example.senhas.controller;

import com.example.senhas.config.exceptions.MensagemPersonalizada;
import com.example.senhas.domain.dto.HistoricoSenhaDTO;
import com.example.senhas.services.SenhaForteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SenhaForteController {

    private final SenhaForteService senhaForteService;

    @Autowired
    public SenhaForteController(SenhaForteService senhaForteService) {
        this.senhaForteService = senhaForteService;
    }

    @PostMapping("/gerarSenha")
    public String gerarSenha(@RequestParam(name = "tamanho", defaultValue = "12") int tamanho,
                             @RequestParam(name = "nome") String nome) {


        isValid(tamanho);
        return senhaForteService.gerarSenha(tamanho, nome);
    }

    @GetMapping("/senhas")
    public List<HistoricoSenhaDTO> getHistoricoSenha() {
        return senhaForteService.getHistoricoSenha();
    }

    public void isValid(int tamanho) {
        if (tamanho < 4 || tamanho > 15) {
            throw new MensagemPersonalizada(
                    "Senha menor que 4 digitos ou maior que 15!",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}