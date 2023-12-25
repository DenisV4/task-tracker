package com.example.tasktracker.web.controller;

import com.example.tasktracker.mapper.TaskMapper;
import com.example.tasktracker.service.TaskService;
import com.example.tasktracker.web.dto.TaskResponse;
import com.example.tasktracker.web.dto.TaskUpsertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public Flux<TaskResponse> getAll() {
        return taskService.findAll()
                .map(taskMapper::taskToResponse);
    }

    @GetMapping("/{id}")
    public Mono<TaskResponse> get(@PathVariable String id) {
        return taskService.findById(id)
                .map(taskMapper::taskToResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TaskResponse> create(@RequestBody TaskUpsertRequest request) {
        return taskService.save(taskMapper.requestToTask(request))
                .map(taskMapper::taskToResponse);
    }

    @PutMapping("/{id}")
    public Mono<TaskResponse> update(@PathVariable String id, @RequestBody TaskUpsertRequest request) {
        return taskService.update(taskMapper.requestToTask(id, request))
                .map(taskMapper::taskToResponse);
    }

    @PutMapping("/{taskId}/{userId}")
    public Mono<TaskResponse> addObserver(@PathVariable String taskId, @PathVariable String userId) {
        return taskService.addObserver(taskId, userId)
                .map(taskMapper::taskToResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id) {
        return taskService.deleteById(id);
    }
}
