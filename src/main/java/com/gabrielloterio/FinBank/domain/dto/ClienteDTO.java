package com.gabrielloterio.FinBank.domain.dto;

public record ClienteDTO(
        Long id,
        String nome,
        String email
) {}