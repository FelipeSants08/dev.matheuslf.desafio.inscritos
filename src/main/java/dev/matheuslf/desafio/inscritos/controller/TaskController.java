package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.domain.dto.TaskRequest;
import dev.matheuslf.desafio.inscritos.domain.dto.TaskStatusDto;
import dev.matheuslf.desafio.inscritos.domain.model.Priority;
import dev.matheuslf.desafio.inscritos.domain.model.Status;
import dev.matheuslf.desafio.inscritos.domain.model.Task;
import dev.matheuslf.desafio.inscritos.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll(@RequestParam(required = false) String title,
                                              @RequestParam(required = false) String description,
                                              @RequestParam(required = false) Status status,
                                              @RequestParam(required = false) Priority priority,
                                              @RequestParam(required = false) Long projectId
    ) {

        TaskRequest filterRequest = new TaskRequest(
                title, description, status, priority, null, projectId
        );

        return ResponseEntity.ok(taskService.findAll(filterRequest));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody TaskRequest taskRequest){
        taskService.saveTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody TaskStatusDto taskStatusDto){
        taskService.updateStatusTask(id, taskStatusDto);
        return ResponseEntity.ok("Status Alterado com sucesso");
    }
}
