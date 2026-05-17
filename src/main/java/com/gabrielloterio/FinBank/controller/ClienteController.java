package com.gabrielloterio.FinBank.controller;

import com.gabrielloterio.FinBank.domain.dto.ClienteDTO;
import com.gabrielloterio.FinBank.domain.model.Cliente;
import com.gabrielloterio.FinBank.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteRequest request) {
        Cliente cliente = clienteService.cadastrar(
                request.nome(),
                request.email(),
                request.senha()
        );
        return ResponseEntity.ok(clienteService.toDTO(cliente));
    }

    record ClienteRequest(String nome, String email, String senha) {}
}