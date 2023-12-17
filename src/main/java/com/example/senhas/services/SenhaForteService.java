package com.example.senhas.services;

import com.example.senhas.domain.dto.HistoricoSenhaDTO;
import com.example.senhas.domain.gerador.GeradorSenha;
import com.example.senhas.domain.model.HistoricoSenha;
import com.example.senhas.domain.repository.HistoricoSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SenhaForteService {

    private final GeradorSenha geradorSenha;
    private final HistoricoSenhaRepository historicoSenhaRepository;

    @Autowired
    public SenhaForteService(GeradorSenha geradorSenha, HistoricoSenhaRepository historicoSenhaRepository) {
        this.geradorSenha = geradorSenha;
        this.historicoSenhaRepository = historicoSenhaRepository;
    }

    public String gerarSenha(int tamanho, String nome, int tipo) {
        String senhaForte = geradorSenha.geradorSenhaForte(tamanho, nome, tipo);
        if (!nome.isEmpty()) {
            salvarNoHistoricoSenhas(senhaForte, nome);
        }
        return senhaForte;
    }

    public void deletarDoHistoricoSenhas(Long id) {
        historicoSenhaRepository.deleteById(id);
    }

    public List<HistoricoSenhaDTO> getHistoricoSenha() {
        List<HistoricoSenha> historicoSenhaList = historicoSenhaRepository.findAll();
        return historicoSenhaList.stream()
                .map(this::converterDTO)
                .collect(Collectors.toList());
    }

    private HistoricoSenhaDTO converterDTO(HistoricoSenha historicoSenha) {
        HistoricoSenhaDTO dto = new HistoricoSenhaDTO();
        dto.setId(historicoSenha.getId());
        dto.setNome(historicoSenha.getNome());
        dto.setSenha(historicoSenha.getSenha());
        dto.setData(historicoSenha.getDataFormatada());
        return dto;
    }

    private void salvarNoHistoricoSenhas(String senha, String nome) {
        HistoricoSenha historicoSenha = new HistoricoSenha();
        historicoSenha.setSenha(senha);
        historicoSenha.setNome(nome);
        historicoSenha.setTimestamp(LocalDateTime.now());
        historicoSenhaRepository.save(historicoSenha);
    }
}
