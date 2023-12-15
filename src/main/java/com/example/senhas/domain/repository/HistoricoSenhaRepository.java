package com.example.senhas.domain.repository;

import com.example.senhas.domain.model.HistoricoSenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoSenhaRepository extends JpaRepository<HistoricoSenha, Long> {
}
