package com.example.senhas.controller;

import com.example.senhas.config.exceptions.MensagemPersonalizada;
import com.example.senhas.domain.dto.HistoricoSenhaDTO;
import com.example.senhas.services.SenhaForteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String gerarSenha(@RequestParam(name = "tamanho") int tamanho,
                             @RequestParam(name = "nome", defaultValue= "") String nome,
                             @RequestParam(name = "tpCaractere") int tpCaractere,
                             @RequestParam(name = "tpNumero", defaultValue= "N") String isNum) {


        isValid(tamanho, tpCaractere, isNum);
        return senhaForteService.gerarSenha(tamanho, nome, tpCaractere, isNum);
    }

    @GetMapping("/senhas")
    public List<HistoricoSenhaDTO> getHistoricoSenha() {
        return senhaForteService.getHistoricoSenha();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarDoHistoricoSenhas(@PathVariable Long id) {
        try {
            senhaForteService.deletarDoHistoricoSenhas(id);
            return new ResponseEntity<>("Usuario deletado com sucesso.", HttpStatus.OK);
        } catch (Exception e) {
            throw new MensagemPersonalizada("Erro ao deletar usuário: ", HttpStatus.BAD_REQUEST);
        }
    }

    public void isValid(int tamanho, int tpCaractere, String isNum) {
        if (tamanho < 4 || tamanho > 15) {
            throw new MensagemPersonalizada(
                    "Senha menor que 4 digitos ou maior que 15!",
                    HttpStatus.BAD_REQUEST
            );
        }
        if (tpCaractere != 1 && tpCaractere != 0) {
            throw new MensagemPersonalizada(
                    "tpCaractere  incorreto! 1 com caractere especial, 0 sem caractere especial!",
                    HttpStatus.BAD_REQUEST
            );
        }

        if(isNum != "S" && isNum != "N") {
            throw new MensagemPersonalizada(
                    "tpNumero invalido!",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}