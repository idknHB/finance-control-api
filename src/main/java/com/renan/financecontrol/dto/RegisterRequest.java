package com.renan.financecontrol.dto;

public record RegisterRequest(
        String nome,
        String email,
        String senha
) {
}
