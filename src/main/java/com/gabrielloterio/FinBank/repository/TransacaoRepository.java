package com.gabrielloterio.FinBank.repository;

import com.gabrielloterio.FinBank.domain.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaId(Long contaId);
}