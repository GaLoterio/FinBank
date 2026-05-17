package com.gabrielloterio.FinBank.service;

import com.gabrielloterio.FinBank.domain.dto.ClienteDTO;
import com.gabrielloterio.FinBank.domain.model.Cliente;
import com.gabrielloterio.FinBank.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaService contaService;
    private final PasswordEncoder passwordEncoder;

    public Cliente cadastrar(String nome, String email, String senha) {
        if (clienteRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email ja cadastrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(passwordEncoder.encode(senha));

        Cliente salvo = clienteRepository.save(cliente);
        contaService.criarConta(salvo);

        return salvo;
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
    }

    public ClienteDTO toDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail()
        );
    }
}