package com.example.tasktracker.mapper;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.web.dto.TaskResponse;
import com.example.tasktracker.web.dto.TaskUpsertRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = UserMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TaskMapper {
    Task requestToTask(TaskUpsertRequest request);

    Task requestToTask(String id, TaskUpsertRequest request);

    TaskResponse taskToResponse(Task task);
}
