package dev.matheuslf.desafio.inscritos.domain.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record ProjectRequest(@NotBlank(message = "nome não pode estar em branco")
                             String name,
         String description,
         @FutureOrPresent
         Date startDate,
         Date endDate) {
}
