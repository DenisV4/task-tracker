package com.example.tasktracker.service.impl;

import com.example.tasktracker.exeption.BadRequestException;
import com.example.tasktracker.exeption.ResourceNotFoundException;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.service.TaskService;
import com.example.tasktracker.service.UserService;
import com.example.tasktracker.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final UserService userService;
    private final TaskRepository taskRepository;

    @Override
    public Flux<Task> findAll() {
        return taskRepository.findAll()
                .flatMap(this::setUsers);
    }

    @Override
    public Mono<Task> findById(String id) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Task with id {0} not found", id)))
                .flatMap(this::setUsers);
    }

    @Override
    public Mono<Task> save(Task task) {
        return checkUsers(task)
                .flatMap(this::toDatabase);
    }

    @Override
    public Mono<Task> update(Task task) {
        return checkUsers(task)
                .zipWith(taskRepository.findById(task.getId()), (t, et) -> {
                    BeanUtil.copyNonNullProperties(t, et);
                    return et;
                })
                .flatMap(this::toDatabase);
    }

    @Override
    public Mono<Task> addObserver(String taskId, String userId) {
        return findById(taskId)
                .zipWith(userService.existsById(userId), (t, exists) -> {
                    if (!exists) {
                        throw new BadRequestException("User with id {0} does not exist", userId);
                    }
                    t.getObserverIds().add(userId);
                    return t;
                })
                .flatMap(this::toDatabase);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return taskRepository.deleteById(id);
    }

    private Mono<Task> toDatabase(Task task) {
        return Mono.just(task)
                .flatMap(taskRepository::save)
                .flatMap(this::setUsers);
    }

    private Mono<Task> setUsers(Task task) {
        return Mono.just(task)
                .zipWith(userService.findById(task.getAuthorId()), (t, u) -> {
                    task.setAuthor(u);
                    return t;
                })
                .zipWith(userService.findById(task.getAssigneeId()), (t, u) -> {
                    task.setAssignee(u);
                    return t;
                })
                .zipWith(userService.findByIdIn(task.getObserverIds()).collectList(), (t, ul) -> {
                    task.setObservers(new HashSet<>(ul));
                    return t;
                });
    }

    private Mono<Task> checkUsers(Task task) {
        var monoTask = Mono.just(task);

        if (task.getAuthorId() != null) {
            monoTask = monoTask
                    .zipWith(userService.existsById(task.getAuthorId()), (t, exists) -> {
                        if (!exists) {
                            throw new BadRequestException("Author with id {0} does not exist", t.getAuthorId());
                        }
                        return t;
                    });
        }

        if (task.getAssigneeId() != null) {
            monoTask = monoTask
                    .zipWith(userService.existsById(task.getAssigneeId()), (t, exists) -> {
                        if (!exists) {
                            throw new BadRequestException("Assignee with id {0} does not exist", t.getAssigneeId());
                        }
                        return t;
                    });
        }

        return monoTask;
    }
}
