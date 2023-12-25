package com.example.tasktracker.web.controller;

import com.example.tasktracker.mapper.UserMapper;
import com.example.tasktracker.service.UserService;
import com.example.tasktracker.web.dto.UserResponse;
import com.example.tasktracker.web.dto.UserUpsertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Flux<UserResponse> getAll() {
        return userService.findAll()
                .map(userMapper::userToResponse);
    }

    @GetMapping("/{id}")
    public Mono<UserResponse> get(@PathVariable String id) {
        return userService.findById(id)
                .map(userMapper::userToResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponse> create(@RequestBody UserUpsertRequest request) {
        return userService.save(userMapper.requestToUser(request))
                .map(userMapper::userToResponse);
    }

    @PutMapping("/{id}")
    public Mono<UserResponse> update(@PathVariable String id, @RequestBody UserUpsertRequest request) {
        return userService.update(userMapper.requestToUser(id, request))
                .map(userMapper::userToResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> delete(@PathVariable String id) {
        return userService.deleteById(id);
    }
}
