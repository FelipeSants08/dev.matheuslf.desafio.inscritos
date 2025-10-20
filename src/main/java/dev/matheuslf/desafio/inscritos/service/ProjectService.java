package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.domain.dto.ProjectRequest;
import dev.matheuslf.desafio.inscritos.domain.model.Project;
import dev.matheuslf.desafio.inscritos.mapper.ProjectMapper;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    public ProjectService(ProjectRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(ProjectRequest projectRequest) {
        repository.save(mapper.projectRequestToProject(projectRequest));
    }


}
