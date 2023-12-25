package com.example.tasktracker.service;

import com.example.tasktracker.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

    Flux<Task> findAll();

    Mono<Task> findById(String id);

    Mono<Task> save(Task task);

    Mono<Task> update(Task task);

    Mono<Task> addObserver(String taskId, String userId);

    Mono<Void> deleteById(String id);
}
