package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.domain.dto.ProjectRequest;
import dev.matheuslf.desafio.inscritos.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProjectRequest projectRequest) {
        projectService.save(projectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
