package dev.matheuslf.desafio.inscritos.domain.dto;

import dev.matheuslf.desafio.inscritos.domain.model.Priority;
import dev.matheuslf.desafio.inscritos.domain.model.Project;
import dev.matheuslf.desafio.inscritos.domain.model.Status;
import dev.matheuslf.desafio.inscritos.domain.model.Task;
import dev.matheuslf.desafio.inscritos.specification.TaskFilter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static dev.matheuslf.desafio.inscritos.specification.TaskFilter.*;

public record TaskRequest (@NotBlank(message = "Campo obrigatório") String title,
                           String description,
                           Status status,
                           Priority priority,
                           @NotNull(message = "Campo obrigatório")
                           @FutureOrPresent(message = "Não pode ser no passado")
                           Date startDate,
                           @NotNull Long projectId){

    public Specification<Task> taskFilter(){
        return taskFilterTitle(title)
                .and(taskFilterDescription(description))
                .and(taskFilterProjectId(projectId))
                .and(taskFilterStatus(status))
                .and(taskFilterPriority(priority));
    }

}
