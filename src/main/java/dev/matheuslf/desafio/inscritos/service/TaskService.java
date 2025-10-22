package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.domain.dto.TaskRequest;
import dev.matheuslf.desafio.inscritos.domain.model.Task;
import dev.matheuslf.desafio.inscritos.mapper.TaskMapper;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository repository, TaskMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public void saveTask(TaskRequest taskRequest){
        repository.save(mapper.taskRequestToTask(taskRequest));
    }

    public List<Task> findAll(TaskRequest taskRequest){
        return repository.findAll(taskRequest.taskFilter());
    }

    public void deleteTask(Long id){
        repository.delete(findById(id));
    }

    public Task findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID inexistente!")
        );
    }

}
