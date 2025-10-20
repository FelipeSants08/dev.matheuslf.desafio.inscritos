package dev.matheuslf.desafio.inscritos.mapper;


import dev.matheuslf.desafio.inscritos.domain.dto.ProjectRequest;
import dev.matheuslf.desafio.inscritos.domain.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {
    @Bean
    ProjectRequest projectToProjectRequest(Project project);
    @Bean
    Project projectRequestToProject(ProjectRequest projectRequest);
}
