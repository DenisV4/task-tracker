package com.example.tasktracker.service;

import com.example.tasktracker.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface UserService {

    Flux<User> findAll();

    Flux<User> findByIdIn(Collection<String> ids);
    Mono<User> findById(String id);

    Mono<User> save(User user);

    Mono<User> update(User user);

    Mono<Void> deleteById(String id);

    Mono<Boolean> existsById(String id);
}
