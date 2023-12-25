package com.example.tasktracker.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpsertRequest {

    private String name;

    private String description;

    private String status;

    private String authorId;

    private String assigneeId;
}
