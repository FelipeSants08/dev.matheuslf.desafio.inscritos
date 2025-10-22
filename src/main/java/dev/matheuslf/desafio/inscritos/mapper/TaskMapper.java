package dev.matheuslf.desafio.inscritos.mapper;

import dev.matheuslf.desafio.inscritos.domain.dto.TaskRequest;
import dev.matheuslf.desafio.inscritos.domain.model.Project;
import dev.matheuslf.desafio.inscritos.domain.model.Task;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TaskMapper {

    @Autowired
    protected ProjectRepository projectRepository;

    public abstract TaskRequest taskToTaskRequest(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "projectId", target = "project")
    public abstract Task taskRequestToTask(TaskRequest taskRequest);

    protected Project getProject(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Projeto não  encontrado")
        );
    }

}
