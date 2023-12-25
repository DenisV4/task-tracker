package com.example.tasktracker.web.dto;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private String id;

    private String name;

    private String description;

    private Instant createdAt;

    private Instant updatedAt;

    private Task.TaskStatus status;

    private User author;

    private User assignee;

    private Set<User> observers;
}
