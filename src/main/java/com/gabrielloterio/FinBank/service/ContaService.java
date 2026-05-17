package com.gabrielloterio.FinBank.service;

import com.gabrielloterio.FinBank.domain.model.*;
import com.gabrielloterio.FinBank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public Conta criarConta(Cliente cliente) {
        Conta conta = new Conta();
        conta.setNumeroConta(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        conta.setSaldo(BigDecimal.ZERO);
        conta.setCliente(cliente);
        return contaRepository.save(conta);
    }

    public Conta consultarSaldo(Long contaId) {
        return contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta nao encontrada"));
    }

    @Transactional
    public Transacao depositar(Long contaId, BigDecimal valor) {
        Conta conta = consultarSaldo(contaId);
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        return registrarTransacao(TipoTransacao.DEPOSITO, valor, conta, null);
    }

    @Transactional
    public Transacao sacar(Long contaId, BigDecimal valor) {
        Conta conta = consultarSaldo(contaId);

        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);

        return registrarTransacao(TipoTransacao.SAQUE, valor, conta, null);
    }

    @Transactional
    public Transacao transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        Conta origem = consultarSaldo(contaOrigemId);
        Conta destino = consultarSaldo(contaDestinoId);

        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));

        contaRepository.save(origem);
        contaRepository.save(destino);

        return registrarTransacao(TipoTransacao.TRANSFERENCIA, valor, origem, destino);
    }

    public List<Transacao> extrato(Long contaId) {
        return transacaoRepository.findByContaId(contaId);
    }

    private Transacao registrarTransacao(TipoTransacao tipo, BigDecimal valor, Conta conta, Conta destino) {
        Transacao transacao = new Transacao();
        transacao.setTipo(tipo);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setConta(conta);
        transacao.setContaDestino(destino);
        return transacaoRepository.save(transacao);
    }
}