package dev.matheuslf.desafio.inscritos.domain.dto;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
