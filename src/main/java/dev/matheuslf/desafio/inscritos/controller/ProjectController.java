package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.domain.dto.ProjectRequest;
import dev.matheuslf.desafio.inscritos.domain.model.Project;
import dev.matheuslf.desafio.inscritos.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ProjectRequest projectRequest) {
        projectService.save(projectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<Project>> findAll(@RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false,defaultValue = "10") int item) {
        return ResponseEntity.ok(projectService.findAll(page, item));
    }

}
