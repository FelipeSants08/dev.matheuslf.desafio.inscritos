package dev.matheuslf.desafio.inscritos.domain.dto;

import dev.matheuslf.desafio.inscritos.domain.model.Priority;
import dev.matheuslf.desafio.inscritos.domain.model.Project;
import dev.matheuslf.desafio.inscritos.domain.model.Status;
import dev.matheuslf.desafio.inscritos.domain.model.Task;
import dev.matheuslf.desafio.inscritos.specification.TaskFilter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

import static dev.matheuslf.desafio.inscritos.specification.TaskFilter.*;

public record TaskRequest (@NotBlank String title,
                           String description,
                           @Enumerated(EnumType.STRING)
                           @NotNull Status status,
                           @Enumerated(EnumType.STRING)
                           @NotNull Priority priority,
                           Date startDate,
                           @NotNull Long projectId){

    public Specification<Task> taskFilter(){
        return taskFilterTitle(title)
                .and(taskFilterDescription(description)
                        .and(taskFilterProjectId(projectId))).and(taskFilterStatus(status));
    }

}
