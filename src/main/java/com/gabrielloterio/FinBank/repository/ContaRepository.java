package com.gabrielloterio.FinBank.repository;

import com.gabrielloterio.FinBank.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumeroConta(String numeroConta);
}