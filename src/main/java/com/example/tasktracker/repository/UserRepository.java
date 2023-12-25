package com.example.tasktracker.repository;

import com.example.tasktracker.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Flux<User> findByIdIn(Collection<String> ids);
}
