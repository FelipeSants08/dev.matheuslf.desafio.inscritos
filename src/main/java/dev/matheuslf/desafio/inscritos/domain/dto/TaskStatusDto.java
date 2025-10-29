package dev.matheuslf.desafio.inscritos.domain.dto;

import dev.matheuslf.desafio.inscritos.domain.model.Status;
import jakarta.validation.constraints.NotNull;

public record TaskStatusDto(@NotNull
                            Status status) {
}
