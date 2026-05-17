package com.gabrielloterio.FinBank.controller;

import com.gabrielloterio.FinBank.domain.model.Conta;
import com.gabrielloterio.FinBank.domain.model.Transacao;
import com.gabrielloterio.FinBank.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @GetMapping("/{id}/saldo")
    public ResponseEntity<Conta> saldo(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.consultarSaldo(id));
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Transacao> depositar(@PathVariable Long id,
                                               @RequestBody ValorRequest request) {
        return ResponseEntity.ok(contaService.depositar(id, request.valor()));
    }

    @PostMapping("/{id}/sacar")
    public ResponseEntity<Transacao> sacar(@PathVariable Long id,
                                           @RequestBody ValorRequest request) {
        return ResponseEntity.ok(contaService.sacar(id, request.valor()));
    }

    @PostMapping("/transferir")
    public ResponseEntity<Transacao> transferir(@RequestBody TransferenciaRequest request) {
        return ResponseEntity.ok(contaService.transferir(
                request.contaOrigemId(),
                request.contaDestinoId(),
                request.valor()
        ));
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<List<Transacao>> extrato(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.extrato(id));
    }

    record ValorRequest(BigDecimal valor) {}
    record TransferenciaRequest(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {}
}